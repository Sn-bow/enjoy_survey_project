package com.enjoy.survey.happyLife.user;

import com.enjoy.survey.happyLife.board.BoardService;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.error.CustomException;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import com.enjoy.survey.happyLife.user.dto.UserSimpleInfoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // TODO : user 정보 변경 API
    @PostMapping("/user/modify/user_info")
    public String userInfoModify(@RequestBody UserSignUpDto userModifyInfo,@RequestHeader(name = "Authorization") String jwtToken) {
        int result = userService.userInfoModify(userModifyInfo, jwtToken);
        if (result > 0) {
            return "회원 정보가 정상적으로 변경되었습니다";
        }else {
            return "회원 정보 변경을 실패하였습니다.";
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
    ) throws Exception {
        // TODO : order 설정하면 좋을것 같음
        HashMap<String, Object> salfu =userService.getSurveyAttendListForUser(jwtToken, search, page);
        if(salfu.isEmpty()) {
            return salfu;
        }else {
            throw new Exception("잘못된 출력 값입니다.");
        }
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


    // ==============================================================================================================
    // ==============================================================================================================
    // ==============================================================================================================
    // TODO : ERROR 처리 예시 코드
    @GetMapping("/test/test/{id}")
    public ResponseEntity<?> getExample(@PathVariable int id) {
        if(id <=0 ) {
            String errorMessage = "ID는 양의 정수여야 합니다.";
            throw new CustomException("INVALID_ID", errorMessage);
        }
        if(id == 2) {
            String errorMessage = "ID는 2가 존재하지 않습니다.";
            throw new CustomException("NOT_FOUND_ID", errorMessage);
        }

        UserEntity temp = new UserEntity();
        temp.setId(1);
        temp.setBirth("970321");
        temp.setEmail("hello@gamil.com");
        temp.setName("hello");

        return ResponseEntity.ok(temp);
    }

    // 예외 처리를 위한 핸들러
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        HashMap<String, String> invalidError = new HashMap<>();
        invalidError.put(ex.getErrorCode(), ex.getMessage());
        if(ex.getErrorCode().equals("INVALID_ID")) {
            // 403 에러 : 로그인 해라
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidError);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidError);
    }

    // ==============================================================================================================
    // ==============================================================================================================
    // ==============================================================================================================
    // ==============================================================================================================

}
