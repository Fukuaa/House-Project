package com.example.houseproject.Controller;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @GetMapping("/tologin")
    public String tologin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, Model model, String username, String password) {
        if (isBlank(username) || isBlank(password)) {
            model.addAttribute("farmName", "用户名或密码不能为空");
            return "index";
        }
        User user = userService.login(username, password);
        if (user == null) {
            model.addAttribute("farmName", "用户名或密码错误");
            return "index";
        }
        request.changeSessionId();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("dengji", user.getDengji());
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting(user.getUsername()));
        return "index1";
    }

    @PostMapping("/zhuChe")
    public String zhuche(Model model, String username, String password, String passwordConfirm) {
        if (isBlank(username) || isBlank(password)) {
            model.addAttribute("name", "用户名或密码不能为空");
            return "zhuche";
        }
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("name", "两次密码不一致");
            return "zhuche";
        }
        if (userService.querybyusername(username) != null) {
            String name = "用户名" + username + "已存在";
            model.addAttribute("name", name);
            return "zhuche";
        }
        userService.addUser(username, password);
        return "index";
    }

    @GetMapping("/goZhuChe")
    public String gozhuche() {
        return "zhuche";
    }

    @GetMapping("/goxiugai")
    public String goxiugai(HttpSession session, int hid) {
        session.setAttribute("f", hid);
        return "xiugai";
    }

    @PostMapping("/xiugai")
    public String xiugai(Model model, HttpServletRequest request, String dizhi, int mianji, int jiage) {
        Object hid = request.getSession().getAttribute("f");
        if (hid == null) {
            return "redirect:/toindex";
        }
        userService.xiugai(dizhi, mianji, jiage, (Integer) hid);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        return "index1";
    }

    @PostMapping("/shanchu")
    public String shanchu(HttpServletRequest request, Model model, int hid) {
        userService.shanchu(hid);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        return "index1";
    }

    @PostMapping("/addfangzhi")
    public String addfangzhi(HttpServletRequest request, Model model, String dizhi, int mianji, int jiage,
                             String tupian, String zhuangtai) {
        userService.addfangzhi(dizhi, mianji, jiage, tupian, zhuangtai);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        return "index1";
    }

    @GetMapping("/toaddfanzhi")
    public String toaddfangzhi() {
        return "index2";
    }

    @GetMapping("/toindex")
    public String toindex(HttpServletRequest request, Model model) {
        Object u = request.getSession().getAttribute("username");
        if (u == null) {
            return "redirect:/";
        }
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        return "index1";
    }

    @PostMapping("/gaimima")
    public String gaimima(HttpServletRequest request, Model model, String p1, String p2) {
        if (isBlank(p1) || !p1.equals(p2)) {
            model.addAttribute("error", "两次密码不一致");
            Object u = request.getSession().getAttribute("username");
            model.addAttribute("msg1", greeting((String) u));
            return "index3";
        }
        Object u = request.getSession().getAttribute("username");
        userService.gaimima((String) u, p1);
        model.addAttribute("msg1", greeting((String) u));
        return "index";
    }

    @GetMapping("/togm")
    public String togm(HttpServletRequest request, Model model) {
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        return "index3";
    }

    @GetMapping("/zhuxiao")
    public String zhuxiao(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/gaimima1")
    public String gaimima1(Model model, HttpServletRequest request) {
        if (!isAdmin(request.getSession())) {
            return "redirect:/toindex";
        }
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        model.addAttribute("user", userService.getAllUser());
        return "index4";
    }

    @GetMapping("/togm1")
    public String togm1(HttpServletRequest request) {
        if (!isAdmin(request.getSession())) {
            return "redirect:/toindex";
        }
        return "index4";
    }

    @PostMapping("/gai")
    public String gai(String password, String passwordConfirm, int dengji, HttpServletRequest request, Model model) {
        if (!isAdmin(request.getSession())) {
            return "redirect:/toindex";
        }
        if (isBlank(password) || !password.equals(passwordConfirm)) {
            model.addAttribute("error", "两次密码不一致");
            return "xiugai1";
        }
        Object target = request.getSession().getAttribute("xiugai");
        if (target == null) {
            return "redirect:/gaimima1";
        }
        userService.gai((String) target, password, dengji);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        model.addAttribute("user", userService.getAllUser());
        return "index4";
    }

    @PostMapping("/shan")
    public String shan(String username, HttpServletRequest request, Model model) {
        if (!isAdmin(request.getSession())) {
            return "redirect:/toindex";
        }
        userService.shan(username);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        model.addAttribute("user", userService.getAllUser());
        return "index4";
    }

    @GetMapping("/goxiugai1")
    public String goxiugai1(String username, HttpSession session) {
        if (!isAdmin(session)) {
            return "redirect:/toindex";
        }
        session.setAttribute("xiugai", username);
        return "xiugai1";
    }

    private boolean isAdmin(HttpSession session) {
        Object level = session.getAttribute("dengji");
        return level instanceof Integer && ((Integer) level) == 1;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String greeting(String username) {
        return "你好！" + username;
    }
}
