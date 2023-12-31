package com.enjoy.survey.happyLife.user;

import com.enjoy.survey.happyLife.board.BoardService;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import com.enjoy.survey.happyLife.user.dto.UserSimpleInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Tag(name = "유저 컨트롤러", description = "유저에 대한 API")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BoardService boardService;

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


    @PostMapping("/join")
    public String userSignUp(@RequestBody UserSignUpDto userSignUpDto) {
        int result = userService.userSignUp(userSignUpDto);
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

    // TODO : 유저 디테일 출력 : MyPage
    @Operation(summary = "유저 디테일 출력 API", description = "유저 디테일 출력 API")
    @GetMapping("/user/info/detail")
    public UserInfoDto getUserDetail(@RequestHeader(name = "Authorization") String jwtToken) {
        return userService.getUserInfo(jwtToken);
    }

    @Operation(summary = "유저 심플 데이터 출력 API", description = "유저 심플 데이터 출력 API")
    @GetMapping("/user/info/simple")
    public UserSimpleInfoDto getUserSimpleInfo(@RequestHeader(name = "Authorization") String jwtToken) {
        return userService.getUserSimpleInfo(jwtToken);
    }

    // TODO : 유저가 작성한 게시물 리스트 출력
    @Operation(summary = "유저가 작성한 게시물 리스트 출력", description = "유저가 작성한 게시물 리스트 출력 API")
    @GetMapping("/user/board/list/forUser")
    public HashMap<String, Object> getBoardListForUser(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return userService.getBoardListForUser(page, search, order, jwtToken);
    }

    // TODO : 유저가 작성한 댓글 리스트 출력
    @Operation(summary = "유저가 작성한 댓글 리스트 출력", description = "유저가 작성한 댓글 리스트 출력 API")
    @GetMapping("/user/comment/list/forUser")
    public List<CommentEntity> getCommentListForUser(@RequestHeader(name = "Authorization") String jwtToken) {
        return userService.getCommentListForUser(jwtToken);
    }

    // TODO : 유저가 작성한 QnA 리스트 출력
    @Operation(summary = "유저가 작성한 QnA 리스트 출력", description = "유저가 작성한 QnA 리스트 출력 API")
    @PostMapping("/user/qna/list/forUser")
    public List<QnAEntity> getQnAForUser(@RequestHeader(name = "Authorization") String jwtToken) {
        return userService.getQnAListForUser(jwtToken);
    }

    // TODO : 유저가 작성한 설문 리스트 출력
    @Operation(summary = "유저가 생성한 설문 리스트 출력", description = "유저가 생성한 설문 리스트 출력 API")
    @PostMapping("/user/survey/list/forUser")
    public HashMap<String, Object> getSurveyListForUser(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return userService.getSurveyListForUser(page, search, order, jwtToken);
    }

    // TODO : 유저가 참여한 설문 리스트 출력
    @Operation(summary = "유저가 참여한 설문 리스트 출력", description = "유저가 참여한 설문 리스트 출력 API")
    @PostMapping("/user/survey/attend/list/forUser")
    public HashMap<String, Object> getSurveyAttendListForUser(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        return userService.getSurveyAttendListForUser(jwtToken, search, page);
    }

    // TODO : 회원 탈퇴 API
    @Operation(summary = "유저 회원탈퇴 기능", description = "유저 회원탈퇴 기능 API")
    @PostMapping("/user/leave")
    public String leaveUser(@RequestHeader(name = "Authorization") String jwtToken) {
        int result = userService.leaveUser(jwtToken);
        if (result > 0) {
            return "회원탈퇴를 성공적으로 마치셨습니다.";
        }else {
            return "회원탈퇴를 실패하였습니다.";
        }
    }
}
