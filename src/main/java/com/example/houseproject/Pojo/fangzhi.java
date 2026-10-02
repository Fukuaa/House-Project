package com.example.houseproject.Pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode
@Accessors
public class fangzhi {
    private String dizhi;
    private int hid;
    private int mianji;
    private int jiage;
    private String tupian;
    private String zhuangtai;
    private int deleted;
    private Date deletedAt;
}
