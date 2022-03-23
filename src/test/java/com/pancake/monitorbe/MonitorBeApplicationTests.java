package com.pancake.monitorbe;

import com.pancake.monitorbe.controller.param.UserParam;
import com.pancake.monitorbe.dao.UserMapper;
import com.pancake.monitorbe.dao.UserSysMapper;
import com.pancake.monitorbe.model.UserResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MonitorBeApplicationTests {

    @Test
    void contextLoads() {
        ArrayList<ArrayList<Double>> danielC = new ArrayList<>();

        ArrayList<Double> d1 = new ArrayList<>();
        ArrayList<Double> d2 = new ArrayList<>();
        ArrayList<Double> d3 = new ArrayList<>(2);
        d1.add(1.56);
        d1.add(1.23);
        d2.add(5.69);
        d2.add(5.88);

//        for (int i = 0; i < d1.size(); i++){
//            for (int j = i; j < d1.size(); j++){
//                d3.set(j, d1.get(i));
//            }
//        }
//        System.out.println("d1:"+d1.toString()+" d3:"+d3.toString());
    }

}
