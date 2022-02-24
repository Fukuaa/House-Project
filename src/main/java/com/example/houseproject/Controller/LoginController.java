package com.example.houseproject.Controller;

import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
}
