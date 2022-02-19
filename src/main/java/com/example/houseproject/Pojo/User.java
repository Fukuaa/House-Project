package com.example.houseproject.Pojo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode
@Accessors
public class User {
    private int id;
    private String username;
    private String password;
}
