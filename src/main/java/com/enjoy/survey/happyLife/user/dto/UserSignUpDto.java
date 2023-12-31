package com.enjoy.survey.happyLife.user.dto;


import lombok.Data;

import java.util.List;

@Data
public class UserSignUpDto {

    String username;
    String password;
    String name;
    String email;
    String role;
    String birth;
    String gender;
    List<Integer> listTopicId;
}
