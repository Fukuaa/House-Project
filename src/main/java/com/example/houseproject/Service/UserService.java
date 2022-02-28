package com.example.houseproject.Service;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    List<User> querybyname();
    List getall();
    User querybyname(String username, String password);
    int addUser(String username,String password);
}
