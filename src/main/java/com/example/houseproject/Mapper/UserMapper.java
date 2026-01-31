package com.example.houseproject.Mapper;

import com.example.houseproject.Pojo.User;
import com.example.houseproject.Pojo.fangzhi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper//代表一个mybatis的mapper类
//@Repository
public interface UserMapper{
    List<User> getAllUser();

    User querybyname(@Param("username") String username, @Param("password") String password);
    List getall();
    int addUser(@Param("username") String username, @Param("password") String password);
    int xiugai(@Param("dizhi") String dizhi, @Param("mianji") int mianji, @Param("jiage") int jiage, @Param("hid") int hid);
    int shanchu(@Param("hid") int hid);

    fangzhi querybyid(@Param("hid") int hid);

    void addfangzhi(@Param("dizhi") String dizhi, @Param("mianji") int mianji, @Param("jiage") int jiage,
                    @Param("tupian") String tupian, @Param("zhuangtai") String zhuangtai);

    void gaimima(@Param("username") String username, @Param("password") String password);

    User querybyusername(@Param("username") String username);

    int gai(@Param("username") String username, @Param("password") String password, @Param("dengji") int dengji);

    int shan(@Param("username") String username);
}
