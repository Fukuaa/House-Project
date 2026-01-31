package com.example.houseproject.Controller;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Value("${app.upload-dir:uploads}")
    private String uploadDir;

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
            log.warn("Login failed: missing credentials");
            model.addAttribute("farmName", "用户名或密码不能为空");
            return "index";
        }
        User user = userService.login(username, password);
        if (user == null) {
            log.warn("Login failed: username={}", username);
            model.addAttribute("farmName", "用户名或密码错误");
            return "index";
        }
        request.changeSessionId();
        session.setAttribute("username", user.getUsername());
        session.setAttribute("dengji", user.getDengji());
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting(user.getUsername()));
        log.info("Login success: username={}", user.getUsername());
        return "index1";
    }

    @PostMapping("/zhuChe")
    public String zhuche(Model model, String username, String password, String passwordConfirm) {
        if (isBlank(username) || isBlank(password)) {
            log.warn("Register failed: missing credentials");
            model.addAttribute("name", "用户名或密码不能为空");
            return "zhuche";
        }
        if (!password.equals(passwordConfirm)) {
            log.warn("Register failed: password mismatch username={}", username);
            model.addAttribute("name", "两次密码不一致");
            return "zhuche";
        }
        if (userService.querybyusername(username) != null) {
            String name = "用户名" + username + "已存在";
            model.addAttribute("name", name);
            log.warn("Register failed: username exists {}", username);
            return "zhuche";
        }
        userService.addUser(username, password);
        log.info("Register success: username={}", username);
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
            log.warn("Update house failed: missing hid in session");
            return "redirect:/toindex";
        }
        userService.xiugai(dizhi, mianji, jiage, (Integer) hid);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        log.info("House updated: hid={}, username={}", hid, u);
        return "index1";
    }

    @PostMapping("/shanchu")
    public String shanchu(HttpServletRequest request, Model model, int hid) {
        userService.shanchu(hid);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        log.info("House deleted: hid={}, username={}", hid, u);
        return "index1";
    }

    @PostMapping("/addfangzhi")
    public String addfangzhi(HttpServletRequest request, Model model, String dizhi, int mianji, int jiage,
                             MultipartFile file, String tupian, String zhuangtai) {
        String imageUrl = saveUpload(file);
        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = (tupian == null || tupian.trim().isEmpty()) ? "/images/tu.jpg" : tupian.trim();
        }
        userService.addfangzhi(dizhi, mianji, jiage, imageUrl, zhuangtai);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg", userService.getall());
        model.addAttribute("msg1", greeting((String) u));
        log.info("House created: username={}, dizhi={}, mianji={}, jiage={}, zhuangtai={}, image={}",
                u, dizhi, mianji, jiage, zhuangtai, imageUrl);
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
            log.warn("Access toindex denied: not logged in");
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
            log.warn("Password change failed: mismatch");
            return "index3";
        }
        Object u = request.getSession().getAttribute("username");
        userService.gaimima((String) u, p1);
        model.addAttribute("msg1", greeting((String) u));
        log.info("Password changed: username={}", u);
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
        Object u = session == null ? null : session.getAttribute("username");
        if (session != null) {
            session.invalidate();
        }
        log.info("Logout: username={}", u);
        return "index";
    }

    @GetMapping("/gaimima1")
    public String gaimima1(Model model, HttpServletRequest request) {
        if (!isAdmin(request.getSession())) {
            log.warn("Admin page denied: not admin");
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
            log.warn("Admin page denied: not admin");
            return "redirect:/toindex";
        }
        return "index4";
    }

    @PostMapping("/gai")
    public String gai(String password, String passwordConfirm, int dengji, HttpServletRequest request, Model model) {
        if (!isAdmin(request.getSession())) {
            log.warn("Admin update denied: not admin");
            return "redirect:/toindex";
        }
        if (isBlank(password) || !password.equals(passwordConfirm)) {
            model.addAttribute("error", "两次密码不一致");
            log.warn("Admin update failed: password mismatch");
            return "xiugai1";
        }
        Object target = request.getSession().getAttribute("xiugai");
        if (target == null) {
            log.warn("Admin update failed: missing target");
            return "redirect:/gaimima1";
        }
        userService.gai((String) target, password, dengji);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        model.addAttribute("user", userService.getAllUser());
        log.info("Admin updated user: admin={}, target={}, dengji={}", u, target, dengji);
        return "index4";
    }

    @PostMapping("/shan")
    public String shan(String username, HttpServletRequest request, Model model) {
        if (!isAdmin(request.getSession())) {
            log.warn("Admin delete denied: not admin");
            return "redirect:/toindex";
        }
        userService.shan(username);
        Object u = request.getSession().getAttribute("username");
        model.addAttribute("msg1", greeting((String) u));
        model.addAttribute("user", userService.getAllUser());
        log.info("Admin deleted user: admin={}, target={}", u, username);
        return "index4";
    }

    @GetMapping("/goxiugai1")
    public String goxiugai1(String username, HttpSession session) {
        if (!isAdmin(session)) {
            log.warn("Admin update page denied: not admin");
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

    private String saveUpload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        String contentType = file.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            log.warn("Upload rejected: invalid content type {}", contentType);
            return null;
        }
        String original = file.getOriginalFilename();
        String extension = "";
        if (original != null && original.contains(".")) {
            extension = original.substring(original.lastIndexOf('.')).toLowerCase();
        }
        String filename = UUID.randomUUID().toString().replace("-", "") + extension;
        Path uploadRoot = Paths.get(uploadDir);
        if (!uploadRoot.isAbsolute()) {
            uploadRoot = Paths.get(System.getProperty("user.dir")).resolve(uploadRoot);
        }
        try {
            Files.createDirectories(uploadRoot);
            Path target = uploadRoot.resolve(filename);
            file.transferTo(target.toFile());
            log.info("Upload success: name={}, size={} bytes", filename, file.getSize());
            return "/uploads/" + filename;
        } catch (IOException e) {
            log.error("Upload failed", e);
            return null;
        }
    }
}
