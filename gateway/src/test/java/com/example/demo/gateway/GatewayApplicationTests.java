package com.example.demo.gateway;

import org.apache.dubbo.common.json.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatewayApplicationTests {

    @Test
    public void contextLoads() throws IOException {

        Map<String, Object> map = new HashMap<>();

        map.put("11", "fdfd");
        map.put("22", "fdfsdfds");

        String jsonstr = JSON.json(map);
        System.out.println(jsonstr);
    }

}
