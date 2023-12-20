package com.enjoy.survey.happyLife.user.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoDto {
    int id;
    String username;
    String name;
    String email;
    String birth;
    String gender;
    Timestamp create_date;
    boolean delete_state;
}
