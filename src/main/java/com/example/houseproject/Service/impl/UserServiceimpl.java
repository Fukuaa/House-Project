package com.example.houseproject.Service.impl;


import com.example.houseproject.Mapper.UserMapper;
import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;
import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> querybyname() {
        return new ArrayList<>();
    }

    @Override
    public List getall() {
        return userMapper.getall();
    }

    @Override
    public List getDeleted() {
        return userMapper.getDeleted();
    }

    @Override
    public User querybyname(String username, String password) {
        return login(username, password);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        User user = userMapper.querybyusername(username);
        if (user == null || user.getPassword() == null) {
            return null;
        }
        String stored = user.getPassword();
        if (looksLikeBcrypt(stored)) {
            return passwordEncoder.matches(password, stored) ? user : null;
        }
        if (stored.equals(password)) {
            String encoded = passwordEncoder.encode(password);
            userMapper.gaimima(username, encoded);
            user.setPassword(encoded);
            return user;
        }
        return null;
    }

    @Override
    public int addUser(String username, String password) {
        String encoded = passwordEncoder.encode(password);
        return userMapper.addUser(username, encoded);
    }

    @Override
    public int xiugai(String dizhi, int mianji, int jiage, String zhuangtai,
                      String jiaju, String shuidian, String zuqi,
                      String zhuangxiu, String chanquan, String wuye, int hid) {
        return userMapper.xiugai(dizhi, mianji, jiage, zhuangtai, jiaju, shuidian, zuqi, zhuangxiu, chanquan, wuye, hid);
    }

    @Override
    public int shanchu(int hid) {
        return userMapper.shanchu(hid);
    }

    @Override
    public int restore(int hid) {
        fangzhi house = userMapper.querybyid(hid);
        if (house == null || "已售出".equals(house.getZhuangtai())) {
            return 0;
        }
        return userMapper.restore(hid);
    }

    @Override
    public int purge(int hid) {
        return userMapper.purge(hid);
    }

    @Override
    public fangzhi querybyid(int hid) {
        return userMapper.querybyid(hid);
    }

    @Override
    public void addfangzhi(String dizhi, int mianji, int jiage, String tupian, String zhuangtai,
                           String jiaju, String shuidian, String zuqi,
                           String zhuangxiu, String chanquan, String wuye) {
        userMapper.addfangzhi(dizhi, mianji, jiage, tupian, zhuangtai, jiaju, shuidian, zuqi, zhuangxiu, chanquan, wuye);
    }

    @Override
    public void gaimima(String u,String p) {
        String encoded = passwordEncoder.encode(p);
        userMapper.gaimima(u, encoded);
    }

    @Override
    public User querybyusername(String username) {
        return userMapper.querybyusername(username);
    }

    @Override
    public int gai(String nameuser, String password, int dengji) {
        String encoded = passwordEncoder.encode(password);
        return userMapper.gai(nameuser, encoded, dengji);
    }

    @Override
    public int shan(String nameuser) {
        return userMapper.shan(nameuser);
    }

    private boolean looksLikeBcrypt(String value) {
        return value.startsWith("$2a$") || value.startsWith("$2b$") || value.startsWith("$2y$");
    }
}
