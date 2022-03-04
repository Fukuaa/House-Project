package com.example.houseproject.Controller;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;
import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpSession session, Model model, String username, String password){
        User user = userService.querybyname(username,password);
        String username1 = "你好！"+ username;
        session.setAttribute("username",username);
        session.setAttribute("pwd",password);
        model.addAttribute("msg",userService.getall());
        model.addAttribute("msg1",username1);
        if (user==null){
            return "500";
        }else {
            return "index1";
        }
    }
    @RequestMapping("/zhuChe")
    public String zhuche(String username, String password){
        userService.addUser(username,password);
        return "index";
    }
    @RequestMapping("/goZhuChe")
    public String gozhuche(){
        return "zhuche";
    }
    @RequestMapping("/goxiugai")
    public String goxiugai(HttpSession session,int hid){
        System.out.println(hid);
        session.setAttribute("f",hid);
        return "xiugai";
    }
    @RequestMapping("/xiugai")
    public String xiugai(Model model,HttpServletRequest request,String dizhi,int mianji,int jiage){
        int hid = (Integer) request.getSession().getAttribute("f") ;
        userService.xiugai(dizhi,mianji,jiage,hid);
        System.out.println(dizhi+mianji+jiage+hid);
        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");
        userService.querybyname((String)u,(String)p);
        String username1 = "你好！"+ u;
        model.addAttribute("msg",userService.getall());
        model.addAttribute("msg1",username1);

        return "index1";
    }
    @RequestMapping("/shanchu")
    public String shanchu(HttpServletRequest request, Model model, int hid){

        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");
        userService.shanchu(hid);
        userService.querybyname((String)u,(String)p);
        String username1 = "你好！"+ u;
        model.addAttribute("msg",userService.getall());
        model.addAttribute("msg1",username1);
        return "index1";
    }
    @RequestMapping("/addfangzhi")
    public String addfangzhi(HttpServletRequest request, Model model,String dizhi,int mianji,int jiage,String tupian){
        userService.addfangzhi(dizhi,mianji,jiage,tupian);
        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");
        userService.querybyname((String)u,(String)p);
        String username1 = "你好！"+ u;
        model.addAttribute("msg",userService.getall());
        model.addAttribute("msg1",username1);
        return "index1";
    }

}
