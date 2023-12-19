package com.enjoy.survey.happyLife.user;

import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/user/userInfo")
    public String getUserinfo() {
        return "userInfo";
    }
    @GetMapping("/user/simple/userInfo")
    public String getSimpleUserInfo() {
        return "simple user information";
    }

//    // TODO : 유저 디테일 출력 : MyPage
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp1() {}
//
//    // TODO : 유저가 작성한 게시물 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp2() {}
//
//    // TODO : 유저가 작성한 댓글 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp3() {}
//
//    // TODO : 유저가 작성한 1 대 1 리스트 문의 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp4() {}
//
//    // TODO : 유저가 작성한 QnA 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp5() {}
//
//    // TODO : 유저가 작성한 설문 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp6() {}
//
//    // TODO : 유저가 참여한 설문 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp7() {}
//
//    // TODO : 회원 탈퇴 API
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp8() {}
}
