package com.enjoy.survey.happyLife.user;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserEntity {

    int id;
    String email;
    String username;
    String password;
    String birth;
    String gender;
    Timestamp create_date;
    boolean delete_state;
    String role;

}
