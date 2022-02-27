package com.example.houseproject;

import com.example.houseproject.Mapper.UserMapper;
import com.example.houseproject.Pojo.fangzhi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HouseProjectApplicationTests {
    @Resource
    UserMapper userMapper;
    @Test
    void contextLoads() {
        List<fangzhi> list = userMapper.getall();
        for (fangzhi l : list) {
            System.out.println(l);
        }
        fangzhi fangzhi = new fangzhi();
        System.out.println(fangzhi);
        /*User user = userMapper.querybyname("zqf","123");
        System.out.println(user);*/
    }

}
