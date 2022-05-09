package com.pancake.monitorbe.service;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * SOAP服务 实时接收workinfo、心跳等信息（使用CXF框架）
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/5/4 1:06
 */
@Component
@WebService(targetNamespace = "http://monitorbe.pancake.com/")
public interface SoapService {

    /**
     * 设备注册
     * @param devcode
     * @return
     */
    @WebMethod
    public String devRegist(@WebParam(name = "devcode") String devcode);

    /**
     * 接收心跳 （sysCode缺省）
     * @param tm_name
     */
    @WebMethod
    public String receiveHeartbeat(@WebParam(name = "sys_code") String sys_code, @WebParam(name = "sys_name") String sys_name, @WebParam(name = "tm_code") String tm_code, @WebParam(name = "tm_name") String tm_name);

    /**
     * 接收心跳
     * @param tm_name
     */
    @WebMethod
    public String receiveHeartbeat2(@WebParam(name = "sys_code") String sys_code, @WebParam(name = "sys_name") String sys_name, @WebParam(name = "tm_code") String tm_code, @WebParam(name = "tm_name") String tm_name);

    /**
     * 接收处理结果（sysCode缺省）
     * @Title: receiveDealResult
     * @Description: 接收处理结果
     * @param: @param devcode
     * @param: @param table
     * @param: @param successNum
     * @return: void
     * @throws
     */
    @WebMethod
    public String receiveDealResult(@WebParam(name = "sys_code") String sys_code, @WebParam(name = "sys_name") String sys_name, @WebParam(name = "tm_code") String tm_code, @WebParam(name = "tm_name") String tm_name, @WebParam(name = "work_info") String work_info);

    /**
     * 接收处理结果
     * @Title: receiveDealResult
     * @Description: 接收处理结果
     * @param: @param devcode
     * @param: @param table
     * @param: @param successNum
     * @return: void
     * @throws
     */
    @WebMethod
    public String receiveDealResult2(@WebParam(name = "sys_code") String sys_code, @WebParam(name = "sys_name") String sys_name, @WebParam(name = "tm_code") String tm_code, @WebParam(name = "tm_name") String tm_name, @WebParam(name = "work_info") String work_info);

    @WebMethod
    public String soapTest(@WebParam(name = "inStr") String inStr);
}
