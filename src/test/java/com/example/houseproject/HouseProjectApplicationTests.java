package com.example.houseproject;

import com.example.houseproject.Mapper.UserMapper;
import com.example.houseproject.Pojo.fangzhi;
import com.example.houseproject.Service.UserService;
import com.example.houseproject.Service.impl.UserServiceimpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HouseProjectApplicationTests {
    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        userService.addfangzhi("1",1,1,"1","1");
    }

}