package com.example.houseproject.Service.impl;


import com.example.houseproject.Mapper.UserMapper;
import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;
import com.example.houseproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public List<User> querybyname() {
        return null;
    }

    @Override
    public List getall() {
        return userMapper.getall();
    }

    @Override
    public User querybyname(String username, String password) {
        return userMapper.querybyname(username,password);
    }

    @Override
    public int addUser(String username, String password) {
        return userMapper.addUser(username,password);
    }

    @Override
    public int xiugai(String dizhi,int mianji,int jiage,int hid) {
        return userMapper.xiugai(dizhi,mianji,jiage,hid);
    }

    @Override
    public int shanchu(int hid) {
        return userMapper.shanchu(hid);
    }

    @Override
    public fangzhi querybyid(int hid) {
        return userMapper.querybyid(hid);
    }

    @Override
    public void addfangzhi(String dizhi, int mianji, int jiage, String tupian, String zhuangtai) {
        userMapper.addfangzhi(dizhi,mianji,jiage,tupian,zhuangtai);
    }

    @Override
    public void gaimima(String u,String p) {
        userMapper.gaimima(u,p);
    }

    @Override
    public User querybyusername(String username) {
        return userMapper.querybyusername(username);
    }

    @Override
    public int gai(String nameuser, String password, int dengji) {
        return userMapper.gai(nameuser,password,dengji);
    }

    @Override
    public int shan(String nameuser) {
        return userMapper.shan(nameuser);
    }

}
