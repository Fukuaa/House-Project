package com.example.houseproject.Mapper;

import com.example.houseproject.Pojo.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;



import java.util.List;

@Mapper//代表一个mybatis的mapper类
@Repository
public interface UserMapper{
    List<User> getAllUser();
}
