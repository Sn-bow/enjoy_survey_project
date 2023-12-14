package com.enjoy.survey.happyLife.User;


import com.enjoy.survey.happyLife.User.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity user() {
        return userDao.findByUsername("tester");
    }


    public int userSignUp(UserSignUpDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userDao.signUp(user);
    }

    public void selectTopic(String username, List<Integer> listSelectTopicId) {
        int userId = userDao.findByUsername(username).getId();
        for (int topic : listSelectTopicId) {
            userDao.saveSelectedTopic(userId, topic);
        }
    }


}
