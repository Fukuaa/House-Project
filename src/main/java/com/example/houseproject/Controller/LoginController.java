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
import java.util.*;

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
            session.setAttribute("dengji",user.getDengji());
            return "index1";
        }
    }
    @RequestMapping("/zhuChe")
    public String zhuche(Model model,String username, String password){
        if (userService.querybyusername(username)!=null){
            String name ="用户名"+username+"已存在";
            model.addAttribute("name",name);
            return "zhuche";
        }else {
            userService.addUser(username,password);
            return "index";
        }
    }
    @RequestMapping("/goZhuChe")
    public String gozhuche(){
        return "zhuche";
    }
    @RequestMapping("/goxiugai")
    public String goxiugai(HttpSession session,int hid){
        session.setAttribute("f",hid);
        return "xiugai";
    }
    @RequestMapping("/xiugai")
    public String xiugai(Model model,HttpServletRequest request,String dizhi,int mianji,int jiage){
        int hid = (Integer) request.getSession().getAttribute("f") ;
        userService.xiugai(dizhi,mianji,jiage,hid);
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
    @RequestMapping("/toaddfanzhi")
    public String toaddfangzhi(){
        return "index2";
    }
    @RequestMapping("/toindex")
    public String toindex(HttpServletRequest request, Model model){
        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");
        userService.querybyname((String)u,(String)p);
        String username1 = "你好！"+ u;
        model.addAttribute("msg",userService.getall());
        model.addAttribute("msg1",username1);
        return "index1";
    }
    @RequestMapping("/gaimima")
    public String gaimima(HttpServletRequest request, Model model,String p1){
        Object u = request.getSession().getAttribute("username");
        userService.gaimima((String)u,p1);
        String username1 = "你好！"+ u;
        model.addAttribute("msg1",username1);
        return "index";
    }
    @RequestMapping("/togm")
    public String togm(HttpServletRequest request, Model model){
        Object u = request.getSession().getAttribute("username");
        String username1 = "你好！"+ u;
        model.addAttribute("msg1",username1);
        return "index3";
    }
    @RequestMapping("/zhuxiao")
    public String zhuxiao(HttpServletRequest request){
        Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
        return "index";
    }
    @RequestMapping("/gaimima1")
    public String gaimima1(Model model,HttpServletRequest request,HttpSession session){
        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");
        User user = userService.querybyname((String)u,(String)p);
        Object o = request.getSession().getAttribute("dengji");
        String username1 = "你好！"+ u;
        model.addAttribute("msg1",username1);
        int i =   (int)request.getSession().getAttribute("dengji");
        if(i==1){
            model.addAttribute("user",userService.getAllUser());
        }
        return "index4";
    }
    @RequestMapping("/togm1")
    public String togm1(){
        return "index4";
    }
    @RequestMapping("/gai")
    public String gai(String password,int dengji,HttpServletRequest request,Model model){
        Object o =  request.getSession().getAttribute("xiugai");
        userService.gai((String)o,password,dengji);
        Object u = request.getSession().getAttribute("username");
        Object p = request.getSession().getAttribute("pwd");

        String username1 = "你好！"+ u;
        int i =   (int)request.getSession().getAttribute("dengji");
        if(i==1){
            model.addAttribute("user",userService.getAllUser());
        }
        model.addAttribute("msg1",username1);
        return "index4";
    }
    @RequestMapping("/shan")
    public String shan(String username,HttpServletRequest request, Model model){
        userService.shan(username);
        Object u = request.getSession().getAttribute("username");


        String username1 = "你好！"+ u;

        model.addAttribute("msg1",username1);
        int i =   (int)request.getSession().getAttribute("dengji");
        if(i==1){
            model.addAttribute("user",userService.getAllUser());
        }
        return "index4";
    }
    @RequestMapping("/goxiugai1")
    public String goxiugai(String username,HttpSession session){
        session.setAttribute("xiugai",username);
        return "xiugai1";
    }


}
