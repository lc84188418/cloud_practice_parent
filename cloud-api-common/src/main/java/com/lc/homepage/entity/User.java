package com.lc.homepage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private String policeNum;
    private String sex;
    private Integer age;
    private String nominal;
    private String portrait;
    private String tel;
    private String politicalOutlook;

}