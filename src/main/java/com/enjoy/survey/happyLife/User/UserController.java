package com.enjoy.survey.happyLife.User;

import com.enjoy.survey.happyLife.User.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "유저 컨트롤러", description = "유저에 대한 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/test")
    public String userTest() {
        return "user role test complete";
    }
    @GetMapping("/manager/test")
    public String managerTest() {
        return "manager role test complete";
    }
    @GetMapping("/admin/test")
    public String adminTest() {
        return "admin role test complete";
    }


    @PostMapping("/user/join")
    public String userSignUp(@RequestBody UserSignUpDto userSignUpDto) {
        int result = userService.userSignUp(userSignUpDto);
        for (int temp : userSignUpDto.getListTopicId()) {
            System.out.println(temp);
        }
        if(result > 0) {
            userService.selectTopic(userSignUpDto.getUsername(), userSignUpDto.getListTopicId());
            return "complete signUp";
        }else {
            return "fail signUp";
        }
    }
}
