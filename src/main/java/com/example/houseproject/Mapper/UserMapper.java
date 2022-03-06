package com.example.houseproject.Mapper;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;


import java.util.List;

//@Mapper//代表一个mybatis的mapper类
//@Repository
public interface UserMapper{
    List<User> getAllUser();

    User querybyname(String username, String password);
    List getall();
    int addUser(String username,String password);
    int xiugai(String dizhi,int mianji,int jiage,int hid);
    int shanchu(int hid);

    fangzhi querybyid(int hid);

    void addfangzhi(String dizhi, int mianji, int jiage, String tupian);

    void gaimima(String username,String password);

    User querybyusername(String username);

    int gai(String username, String password, int dengji);

    int shan(String nameuser);
}
