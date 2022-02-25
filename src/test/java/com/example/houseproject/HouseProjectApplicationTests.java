package com.example.houseproject;

import com.example.houseproject.Mapper.UserMapper;
import com.example.houseproject.Pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

        User user = userMapper.querybyname("zqf","123");
        System.out.println(user);
    }

}
