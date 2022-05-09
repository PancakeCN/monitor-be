package com.pancake.monitorbe.service.impl;

import com.pancake.monitorbe.dao.SysMapper;
import com.pancake.monitorbe.dao.TerminalMapper;
import com.pancake.monitorbe.dao.WorkinfoMapper;
import com.pancake.monitorbe.entity.Sys;
import com.pancake.monitorbe.entity.Terminal;
import com.pancake.monitorbe.entity.WorkInfo;
import com.pancake.monitorbe.service.SoapService;
import lombok.extern.log4j.Log4j2;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * SOAP服务实现类 实时接收workinfo、心跳等信息（使用CXF框架）
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/5/4 1:08
 */
@Component
@WebService(serviceName = "SoapService", targetNamespace = "http://monitorbe.pancake.com/",
        endpointInterface = "com.pancake.monitorbe.service.SoapService")
@Log4j2
public class SoapServiceImpl implements SoapService {

    public static final String SYS_CODE_DEFAULT = "default";
    public static Object dealMap;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Map<String, Terminal> devmap = new HashMap<>();
    public static Set<String> registSet = new HashSet<String>();

    //public Map<String, Terminal> getDevmap()

    @Resource
    TerminalMapper terminalMapper;

    @Resource
    WorkinfoMapper workinfoMapper;

    @Resource
    SysMapper sysMapper;

    /**
     * 设备注册
     *
     * @param devcode
     * @return
     */
    @Override
    public String devRegist(String devcode) {
        //加载注册文件
        loadRegist();
        if (registSet.contains(devcode)) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * 接收心跳 （sysCode缺省）
     *
     * @param
     */
    @Override
    public String receiveHeartbeat(String sys_code, String sys_name, String tm_code, String tm_name) {
        String devmapKey, sysCodeIn;
        //判断sys_code是否传入
        if (sys_code == null) {
            sysCodeIn = SYS_CODE_DEFAULT;
        } else {
            sysCodeIn = sys_code;
        }
        devmapKey = sysCodeIn + "," + tm_code;

        //插入系统表
        insertIntoTbSys(sysCodeIn, sys_name);

        Terminal terminal;
        //判断是否在列表中
        if (devmap.containsKey(devmapKey)) {
            //更新心跳时间
            terminal = devmap.get(devmapKey);
            terminal.setHeartbeat(System.currentTimeMillis());
            try {
                terminalMapper.updateByPrimaryKeySelective(terminal);
            } catch (Exception e) {
                e.printStackTrace();
                return "0";
            }
        } else {
            terminal = new Terminal();
            terminal.setSysCode(sysCodeIn);
            terminal.setTmCode(tm_code);
            terminal.setTmName(tm_name);
            terminal.setTmIp(getTerminalIP());
            terminal.setHeartbeat(System.currentTimeMillis());
            //判断终端是否存在
            try {
                Terminal dbtm = terminalMapper.getTerminalByPrimaryKey(sysCodeIn, tm_code);
                if (dbtm == null) {
                    terminalMapper.insertSelective(terminal);
                } else {
                    terminalMapper.updateByPrimaryKeySelective(terminal);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "0";
            }
        }
        devmap.put(devmapKey, terminal);

        return "1";
    }


    /**
     * 接收心跳
     *
     * @param
     */
    @Override
    public String receiveHeartbeat2(String sys_code, String sys_name, String tm_code, String tm_name) {
        return null;
    }

    /**
     * 接收处理结果 （sysCode缺省）
     *
     * @throws
     * @Title: receiveDealResult
     * @Description: 接收处理结果
     * @param: @param devcode
     * @param: @param table
     * @param: @param successNum
     * @return: void
     */
    @Override
    public String receiveDealResult(String sys_code, String sys_name, String tm_code, String tm_name, String work_info) {
        String sysCodeIn, devmapKey;
        //判断sys_code是否传入
        if (sys_code == null) {
            sysCodeIn = SYS_CODE_DEFAULT;
        } else {
            sysCodeIn = sys_code;
        }
        devmapKey = sysCodeIn + "," + tm_code;

        try {
            //插入系统表
            insertIntoTbSys(sysCodeIn, sys_name);

//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Terminal terminal = null;
            if (devmap.get(devmapKey) == null) {
                terminal = new Terminal();
                terminal.setSysCode(sysCodeIn);
                terminal.setTmCode(tm_code);
                terminal.setTmName(tm_name);
                terminal.setTmIp(getTerminalIP());
                terminal.setHeartbeat(System.currentTimeMillis());
                terminal.setLastUpdateTime(sdf.format(new Date()));
                //判断终端是否存在
                try {
                    Terminal dbtm = terminalMapper.getTerminalByPrimaryKey(sysCodeIn, tm_code);
                    if (dbtm == null) {
                        terminalMapper.insertSelective(terminal);
                    } else {
                        terminalMapper.updateByPrimaryKeySelective(terminal);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "0";
                }
            } else {
                terminal = devmap.get(tm_code);
                terminal.setTmIp(getTerminalIP());
                terminal.setHeartbeat(System.currentTimeMillis());
                terminal.setLastUpdateTime(sdf.format(new Date()));

                try {
                    terminalMapper.updateByPrimaryKeySelective(terminal);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "0";
                }
            }
            devmap.put(devmapKey, terminal);

            WorkInfo workInfo = new WorkInfo(
                    null, sysCodeIn, tm_code, work_info, getTerminalIP(), sdf.format(new Date())
            );

            //插入工作记录
            workinfoMapper.insertSelective(workInfo);
            return "1";
        } catch (Exception e) {
            log.error("接收处理结果失败：" + e.getMessage(), e);
            return "0";
        }
    }

    /**
     * 接收处理结果
     *
     * @throws
     * @Title: receiveDealResult
     * @Description: 接收处理结果
     * @param: @param devcode
     * @param: @param table
     * @param: @param successNum
     * @return: void
     */
    @Override
    public String receiveDealResult2(String sys_code, String sys_name, String tm_code, String tm_name, String work_info) {
        return null;
    }

    @Override
    public String soapTest(String inStr) {

        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest httpServletRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        return inStr + " " + httpServletRequest.getRemoteAddr();
    }


    /**
     * 获取访问终端IP
     *
     * @throws
     * @Title: getTerminalIP
     * @param: @return
     * @return: String
     */
    private String getTerminalIP() {
        Message message = PhaseInterceptorChain.getCurrentMessage();
        HttpServletRequest httpServletRequest = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        return httpServletRequest.getRemoteAddr();
    }

    private void loadRegist() {
        //定位到classpath目录
        String path = this.getClass().getResource("/").getPath();
        //创建文件
        File file = new File(path, "regist.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                registSet.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertIntoTbSys(String sysCodeIn, String sysNameIn) {
        try {
            if (sysMapper.getSystemByPrimaryKey(sysCodeIn) == null) {
                String sysNameAbbr;
                if (sysNameIn == null) {
                    sysNameIn = sysCodeIn;
                    sysNameAbbr = sysCodeIn;
                } else {
                    sysNameAbbr = sysNameIn.substring(0, 4);
                }

                Sys sys = new Sys(
                        sysCodeIn, sysNameIn, sysNameAbbr, 0
                );

                sysMapper.insertSelective(sys);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
