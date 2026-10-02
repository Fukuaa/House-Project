package com.example.houseproject.Service;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    List<User> querybyname();
    List getall();
    List getDeleted();
    User querybyname(String username, String password);
    User login(String username, String password);
    int addUser(String username,String password);
    int xiugai(String dizhi, int mianji, int jiage, String zhuangtai, int hid);
    int shanchu(int hid);
    int restore(int hid);
    int purge(int hid);

    fangzhi querybyid(int hid);

    void addfangzhi(String dizhi, int mianji, int jiage, String tupian, String zhuangtai);

    void gaimima(String u,String p);

    User querybyusername(String username);

    int gai(String nameuser, String password, int dengji);

    int shan(String nameuser);
}
