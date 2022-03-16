package com.pancake.monitorbe.service;

import com.pancake.monitorbe.entity.Sys;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 业务层-系统-接口
 *
 * @author PancakeCN
 * @link https://github.com/PancakeCN
 * @date 2022/3/14 1:16
 */
@Service
public interface SysService {

    /**
     * 业务层-获取所有终端
     *
     * @return java.util.ArrayList<com.pancake.monitorbe.entity.Sys>
     * @author PancakeCN
     * @date 2022/3/14 16:01
     */
    ArrayList<Sys> getAllSystemList();
}
