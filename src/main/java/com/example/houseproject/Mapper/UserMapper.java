package com.example.houseproject.Mapper;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Mapper;



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
}
