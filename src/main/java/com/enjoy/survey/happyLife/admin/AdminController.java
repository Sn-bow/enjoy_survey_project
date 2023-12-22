package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.inquiry.InquiryEntity;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.survey.SurveyEntity;
import com.enjoy.survey.happyLife.user.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ============== 유저 ====================
    // TODO : 유저 삭제 (= 비활성화)
    @Operation(summary = "관리자 계정에서 사용자들 계정 비활성화 API", description = "관리자 계정에서 사용자들 계정 비활성화 API")
    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestBody Integer userId) {
        int result = adminService.deleteUser(userId);
        if (result > 0) {
            return "회원 삭제가 완료되었습니다.";
        }else {
            return "회원 삭제에 실패하였습니다.";
        }
    }

        // TODO : 유저 선택 삭제 (= 비활성화)
    @Operation(summary = "관리자 계정에서 사용자들 계정 리스트 비활성화 API", description = "관리자 계정에서 사용자들 계정 리스트 비활성화 API")
    @PostMapping("/admin/user/delete/list")
    public String choiceDeleteUser(@RequestBody List<Integer> userIds) {
        int result = adminService.choiceDeleteUser(userIds);
        if(result > 0) {
            return "회원 삭제가 완료 되었습니다.";
        }else {
            return "회원 삭제에 실패하였습니다.";
        }
    }

    // TODO : 유저 리스트 출력
    @Operation(summary = "유저 리스트 출력", description = "비활성화된 유저가 포함된 유저 리스트 출력")
    @PostMapping("/admin/user/list")
    public HashMap<String, Object> getUserList(
            @RequestParam(name = "search") String search,
            @RequestParam(name = "page") int page
    ) {
        return adminService.getUserList(search, page);
    }

    // TODO : 유저 디테일 출력
    @Operation(summary = "유저 디테일 출력", description = "비활성화된 유저 포함 유저 디테일 출력 API")
    @PostMapping("/admin/user/detail")
    public UserEntity getUserDetailAdminVer(@RequestParam(name = "userId") int userId) {
        return adminService.getUserDetailAdminVer(userId);
    }

// React 유저 디테일 페이지 출력 사용 예시 코드;
// const [userDetail, setUserDetail] = useState();
// const [boardList, setBoardList] = useState();
// axios.get(
//          "http://localhost:8080/admin/user/detail?userId=3,
//              {
//                  header : {
//                      authorization : cookie.get(authorization)}}})
//                      .response((res) => {
// setUserDetail(res.data);
// axios.get(
//          "http//localhost:8080/admin/board/list?page=1&search=&order=최신 순서&userId=${res.data.userId},
//            {
//                header: {
//                    Authorization : cooke.get(authorization)}
//             })
//                      .response(res => setBoardList(res.data)
//           )
// });

    // ================= 유저 & 게시물, 댓글 & 1대1 문의, QnA & 설문 ===========

    // TODO : 유저가 작성한 게시물 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 게시물 리스트 출력", description = "비활서오하 된 게시물도 보이게됨")
    @PostMapping("/admin/user/board/list")
    public HashMap<String, Object> getBoardListAdminVer(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order,
            @RequestParam(name = "userId") int userId
    ) {
        return adminService.getBoardListAdminVer(page, search, order, userId);
    }

    // TODO : 유저가 작성한 댓글 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 댓글 리스트 출력", description = "유저가 작성한 댓글 리스트 출력 ( 비활성화된 댓글 포함)")
    @PostMapping("/admin/user/comment/list")
    public HashMap<String, Object> getCommentListAdminVer(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return adminService.getCommentListAdminVer(search, userId, page);
    }

    // TODO : 유저가 작성한 1 대 1 리스트 문의 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 1 대 1 리스트 문의 출력", description = "유저가 작성한 1 대 1 리스트 문의 출력 비활성화된 문의 포함")
    @PostMapping("/admin/user/inquiry/list")
    public List<InquiryEntity> getInquiryListAdminVer(@RequestParam(name = "userId") int userId) {
        return adminService.getInquiryListAdminVer(userId);
    }

    // TODO : 유저가 작성한 QnA 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 QnA 리스트 출력", description = "유저가 작성한 QnA 리스트 출력 비활성화 된 QnA 포함")
    @PostMapping("/admin/user/qna/list")
    public List<QnAEntity> getQnAListAdminVer(@RequestParam(name = "userId") int userId) {
        return adminService.getQnAListAdminVer(userId);
    }

    // TODO : 유저가 작성한 설문 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 설문 리스트 출력", description = "유저가 작성한 설문 리스트 출력 비활성화 된 설문 포함")
    @PostMapping("/admin/user/survey/list")
    public List<SurveyEntity> getSurveyListAdminVer(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return adminService.getSurveyListAdminVer(page,search, order, userId);
    }

    // TODO : 유저가 참여한 설문 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 참여한 설문 리스트 출력 ", description = "유저가 참여한 설문 리스트 출력 비활성화 포함")
    @PostMapping("/admin/user/attend/survey/list")
    public HashMap<String, Object> getAttendSurveyListAdminVer(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return adminService.getAttendSurveyListAdminVer(userId, search, page);
    }

    // =============== 게시물, 댓글 ====================

    @Operation(summary = "관리자 페이지 게시물 리스트 출력 (비활성화된 게시물도 출력)", description = "관리자 페이지 게시물 리스트 출력 (비활성화된 게시물도 출력)")
    @PostMapping("/admin/board/list") // 삭제(=비활성화) 된 게시물도 출력
    public HashMap<String, Object> getBoardListAdminVer(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
    ) {
       return adminService.getBoardListAdminVer(page, search, order);
    }

    @Operation(summary = "관리자 페이지 게시물 디테일 출력 (비활성화된 게시물도 출력)", description = "관리자 페이지 게시물 디테일 출력 (비활성화된 게시물도 출력)")
    @PostMapping("/admin/board/detail") // 삭제(=비활성화) 된 게시물도 출력
    public HashMap<String, Object> getBoardDetailAdminVer(@RequestParam(name = "boardId") int boardId) throws Exception {
        return adminService.getBoardDetailAdminVer(boardId);
    }

    // 게시물 삭제 API 는 기존 API 재활용

    // 댓글 삭제 API 는 기존 API 재활용

    // ================== 1대1 문의, QnA =================

    // TODO : 전체 1대1 문의 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp17() {}
//
//    // TODO : 1대1 문의 디테일 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp18() {}
//
//    // TODO : 전체 QnA 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp19() {}
//
//    // TODO : QnA 디테일 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp20() {}
//
//    // TODO : 1대1 문의 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp21() {}
//
//    // TODO : 1대1 문의 선택 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp22() {}
//
//    // TODO : 1대1 문의 답변 생성
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp23() {}
//
//    // TODO : 1대1 문의 답변 수정
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp24() {}
//
//    // TODO : QnA 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp25() {}
//
//    // TODO : QnA 선택 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp26() {}
//
//    // TODO : QnA Answer 생성
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp27() {}
//
//    // TODO : QnA Answer 수정
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp28() {}
//
//    // =================== 설문 =======================
//
//    // TODO : 전체 설문 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp29() {}
//
//    // TODO : 설문 디테일 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp30() {}
//
//    // TODO : 설문 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp31() {}
//
//    // TODO : 설문 선택 삭제
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp32() {}

}
