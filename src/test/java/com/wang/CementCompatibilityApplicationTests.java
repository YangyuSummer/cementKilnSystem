package com.wang;


import com.wang.entity.Plan;
import com.wang.service.MonitorService;
import com.wang.service.PlanService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CementCompatibilityApplicationTests {

    @Resource
    private MonitorService monitorService;

    @Test
    void contextLoads(){

        int i = monitorService.aveHeatValueSludge();

        System.out.println(i);


    }
}
