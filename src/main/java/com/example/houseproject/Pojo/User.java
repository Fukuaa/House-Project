package com.example.houseproject.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
}
