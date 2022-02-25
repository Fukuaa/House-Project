package com.example.houseproject.Service;

import com.example.houseproject.Pojo.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    List<User> querybyname();

    User querybyname(String username, String password);
}
