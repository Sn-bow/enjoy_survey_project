package com.enjoy.survey.happyLife.User.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserSignUpDto {

    String username;
    String password;
    String email;
    String role;
    String birth;
    String gender;
    List<Integer> listTopicId;
}
