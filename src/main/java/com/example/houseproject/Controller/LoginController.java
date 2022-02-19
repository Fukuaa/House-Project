package com.example.houseproject.Controller;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Service.UserService;
import com.example.houseproject.Service.impl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public List<User> login(){
        List list = new ArrayList();
        list = userService.getAllUser();
        System.out.println(list+"******");
        return list;
    }
}
