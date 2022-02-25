package com.example.houseproject.Controller;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(Model model,String username, String password){
        System.out.println(username);
        System.out.println(password);
        User user = userService.querybyname(username,password);
        System.out.println(userService.querybyname(username,password));
        String username1 = "你好！"+ username;
        if (user==null){
            model.addAttribute("msg",username1);
            return "500";
        }else {
            model.addAttribute("msg",username1);
            return "index1";
        }
    }
}
