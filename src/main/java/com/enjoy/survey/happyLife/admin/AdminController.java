package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.admin.dto.QnAAnswerDto;
import com.enjoy.survey.happyLife.board.dto.BoardDeleteDto;
import com.enjoy.survey.happyLife.inquiry.InquiryEntity;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ============== 유저 ====================
    // TODO : 유저 정보 변경
    @PostMapping("/admin/modify/user_info")
    public ResponseEntity<?> userInfoModify(@RequestBody UserSignUpDto userModifyInfo) {
        int result = adminService.userInfoModify(userModifyInfo);
        if (result > 0) {
            return ResponseEntity.ok("회원 정보가 정상적으로 변경되었습니다");
        }else {
            return ResponseEntity.status(401).body("회원 정보 변경 정보를 확인해주세요.");
        }
    }

    // TODO : 유저 삭제 (= 비활성화)
    @Operation(summary = "관리자 계정에서 사용자들 계정 비활성화 API", description = "관리자 계정에서 사용자들 계정 비활성화 API")
    @PostMapping("/admin/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody Integer userId) {
        int result = adminService.deleteUser(userId);
        if (result > 0) {
            return ResponseEntity.ok("회원 삭제가 완료되었습니다.");
        }else {
            return ResponseEntity.status(401).body("회원 아이디가 올바르지 않습니다.");
        }
    }

        // TODO : 유저 선택 삭제 (= 비활성화)
    @Operation(summary = "관리자 계정에서 사용자들 계정 리스트 비활성화 API", description = "관리자 계정에서 사용자들 계정 리스트 비활성화 API")
    @PostMapping("/admin/user/delete/list")
    public ResponseEntity<?> choiceDeleteUser(@RequestBody List<Integer> userIds) {
        int result = adminService.choiceDeleteUser(userIds);
        if(result > 0) {
            return ResponseEntity.ok("회원 삭제가 완료 되었습니다.");
        }else {
            return ResponseEntity.status(401).body("삭제할 회원 아이디가 올바르지 않습니다.");
        }
    }

    // TODO : 유저 리스트 출력
    @Operation(summary = "유저 리스트 출력", description = "비활성화된 유저가 포함된 유저 리스트 출력")
    @GetMapping("/admin/user/list")
    public HashMap<String, Object> getUserList(
            @RequestParam(name = "search") String search,
            @RequestParam(name = "page") int page
    ) {
        return adminService.getUserList(search, page);
    }

    // TODO : 유저 디테일 출력
    @Operation(summary = "유저 디테일 출력", description = "비활성화된 유저 포함 유저 디테일 출력 API")
    @GetMapping("/admin/user/detail")
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
    @Operation(summary = "유저가 작성한 게시물 리스트 출력", description = "비활성화 된 게시물도 보이게됨")
    @GetMapping("/admin/user/board/list")
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
    @GetMapping("/admin/user/comment/list")
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
    @GetMapping("/admin/user/inquiry/list")
    public List<InquiryEntity> getInquiryListAdminVer(@RequestParam(name = "userId") int userId) {
        return adminService.getInquiryListAdminVer(userId);
    }

    // TODO : 유저가 작성한 QnA 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 QnA 리스트 출력", description = "유저가 작성한 QnA 리스트 출력 비활성화 된 QnA 포함")
    @GetMapping("/admin/user/qna/list")
    public List<QnAEntity> getQnAListAdminVer(@RequestParam(name = "userId") int userId) {
        return adminService.getQnAListAdminVer(userId);
    }

    // TODO : 유저가 작성한 설문 리스트 출력 | where delete_state = false 조건 걸지않고
    // TODO : order 수정
    @Operation(summary = "유저가 작성한 설문 리스트 출력", description = "유저가 작성한 설문 리스트 출력 비활성화 된 설문 포함")
    @GetMapping("/admin/user/survey/list")
    public HashMap<String, Object> getSurveyListAdminVer(
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
    @GetMapping("/admin/user/attend/survey/list")
    public HashMap<String, Object> getAttendSurveyListAdminVer(
            // TODO : @RequestParam(name = "order", defaultValue = "최신 순서") String order
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "userId") int userId,
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {
        return adminService.getAttendSurveyListAdminVer(userId, search, page);
    }

    // =============== 게시물, 댓글 ====================

    @Operation(summary = "관리자 페이지 게시물 리스트 출력 (비활성화된 게시물도 출력)", description = "관리자 페이지 게시물 리스트 출력 (비활성화된 게시물도 출력)")
    @GetMapping("/admin/board/list") // 삭제(=비활성화) 된 게시물도 출력
    public HashMap<String, Object> getBoardListAdminVer(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
    ) {
       return adminService.getBoardListAdminVer(page, search, order);
    }

    @Operation(summary = "관리자 페이지 게시물 디테일 출력 (비활성화된 게시물도 출력)", description = "관리자 페이지 게시물 디테일 출력 (비활성화된 게시물도 출력)")
    @GetMapping("/admin/board/detail") // 삭제(=비활성화) 된 게시물도 출력
    public ResponseEntity<?> getBoardDetailAdminVer(
            @RequestParam(name = "boardId") int boardId
    ) throws Exception {
        HashMap<String, Object> boardDetail = adminService.getBoardDetailAdminVer(boardId);
        if (boardDetail.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("err_message", "올바른 boardId 가 아닙니다.");
            return ResponseEntity.status(400).body(error);
        }else {
            return ResponseEntity.ok(boardDetail);
        }
    }


    // 게시물 삭제 API 는 기존 API 재활용
    @Operation(summary = "board 삭제 관리자 버전 API", description = "board 삭제 관리자 버전 API")
    @PostMapping("/admin/board/delete")
    public ResponseEntity<?> deleteBoard(
            @RequestBody Integer boardId
    ) {
        int result = adminService.deleteBoardAdminVer(boardId);
        if (result > 0) {
            return ResponseEntity.ok("성공적으로 삭제에 성공하셨습니다.");
        }else {
            return ResponseEntity.status(400).body("삭제에 실패하셨습니다.");
        }
    }

    @Operation(summary = "board 선택 삭제 관리자 버전 API", description = "board 선택 삭제 관리자 버전 API")
    @PostMapping("/admin/board/deleteList")
    public ResponseEntity<?> choiceDeleteBoard(
            @RequestBody BoardDeleteDto boardDeleteDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        int result = adminService.choiceDeleteBoardAdminVer(boardDeleteDto.getBoardIds());
        if(result > 0) {
            return ResponseEntity.ok("성공적으로 게시글들을 삭제 하였습니다.");
        }else {
            return ResponseEntity.status(400).body("게시글들을 삭제하는데 문제가 발생했습니다.");
        }
    }

    // TODO : 댓글 삭제 API 구현
    @Operation(summary = "댓글 삭제 관리자 버전 API", description = "관리자 버전인 댓글 삭제(비활성화) API")
    @PostMapping("/admin/comment/delete")
    public ResponseEntity<?> deleteCommentAdminVer(
            @RequestBody Integer cmtId
    ) {
        int result = adminService.deleteCommentAdminVer(cmtId);
        if(result > 0) {
            return ResponseEntity.ok("정상적으로 댓글이 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("댓글 삭제에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 리스트 삭제 관리자 버전 API", description = "관리자 버전인 댓글 리스트 삭제(비활성화) API")
    @PostMapping("/admin/comment/list/delete")
    public ResponseEntity<?> deleteCommentListAdminVer(
            @RequestBody List<Integer> cmtIds
    ) {
        int result = adminService.deleteCommentListAdminVer(cmtIds);
        if(result > 0) {
            return ResponseEntity.ok("정상적으로 댓글이 삭제되었습니다.");
        }else {
            return ResponseEntity.status(400).body("댓글 삭제에 실패하였습니다");
        }
    }


    // ================== 1대1 문의, QnA =================

    // TODO : admin 폴더에 생성할 맵핑
    // TODO : 1대1 문의 답변 생성
    @Operation(summary = "1대1 답변 등록", description = "관리자의 1대1 답변 등록 API (답변 수정 API 겸용)")
    @PostMapping("/admin/inquiry/answer")
    public String setInquiryAnswer(@RequestBody InquiryAnswerRegDto inquiryAnswerRegDto) throws Exception {
        int result = adminService.setInquiryAnswer(inquiryAnswerRegDto);
        if (result > 0) {
            return "1대1 문의 답변을 등록하셨습니다";
        } else {
            throw new Exception("1대1 문의 답변 등록에 실패하였습니다.");
        }
    }

     // TODO : 전체 1대1 문의 리스트 출력
    @Operation(summary = "전체 1대1 문의 리스트 출력", description = "전체 1대1 문의 리스트 출력 API ( 비활성화 된 문의 리스트 포함 )")
    @GetMapping("/admin/inquiry/list")
    public HashMap<String, Object> getInquiryListAdminVer(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
    ) {
        return adminService.getInquiryListAdminVer(search, page, order);
    }

//    // TODO : 1대1 문의 디테일 출력 만들어야 함
    @Operation(summary = "1대1 문의 단일 출력", description = "1대1 문의 단일 출력 API (비활성화 된 문의 포함)")
    @GetMapping("/admin/inquiry")
    public ResponseEntity<?> getInquiryAdminVer(
            @RequestParam(name = "inquiryId") Integer inquiryId
    ) {
        InquiryEntity inquiry = adminService.getInquiryAdminVer(inquiryId);
        if(inquiry != null) {
            return ResponseEntity.ok(inquiry);
        }else {
            return ResponseEntity.status(400).body("inquiryId가 존재하지 않습니다.");
        }
    }

    // TODO : 전체 QnA 리스트 출력
    @Operation(summary = "전체 QnA 리스트 출력", description = "전체 QnA 리스트 출력 비활성화 포함")
    @PostMapping("/admin/qna/list")
    public HashMap<String, Object> getQnAListAdminVer(
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
    ) {
        return adminService.getQnAListAdminVer(search, order, page);
    }

    // TODO : QnA 디테일 출력
    @Operation(summary = "QnA detail 출력 API 관리자 버전", description = "QnA detail 출력 API 관리자 버전")
    @GetMapping("/admin/qna/detail")
    public QnAEntity getQnAAdminVer(@RequestParam(name = "qnaId") int qnaId) {
        return adminService.getQnAAdminVer(qnaId);
    }

    // TODO : 1대1 문의 삭제
    @Operation(summary = "1대1 문의 삭제(비활성화)", description = "1대1 문의 삭제 answer 가 존재해도 삭제 가능")
    @PostMapping("/admin/inquiry/delete")
    public String deleteInquiryAdminVer(@RequestBody Integer inquiryId) {
        int result = adminService.deleteInquiryAdminVer(inquiryId);
        if (result > 0) {
            return "1대1 문의 삭제 완료";
        }else {
            return "1대1 문의 삭제 실패";
        }
    }

    // TODO : 1대1 문의 선택 삭제
    @Operation(summary = "1대1 문의 선택 삭제", description = "1대1 문의 선택 삭제 answer가 존재해도 삭제 가능")
    @PostMapping("/admin/inquiry/delete/list")
    public String listDeleteInquiryAdminVer(@RequestBody List<Integer> inquiryIds) {
        int result = adminService.listDeleteInquiryAdminVer(inquiryIds);
        return result > 0 ? "1대1 문의 삭제 완료" : "1대1 문의 삭제 실패";
    }


    // TODO : 1대1 문의 답변 수정 -> 답변등록 API 사용

    // TODO : QnA 삭제
    @Operation(summary = "QnA 삭제", description = "QnA 삭제 answer가 존재해도 삭제가능(비활성화)")
    @PostMapping("/admin/qna/delete")
    public String deleteQnAAdnminVer(@RequestBody Integer qnaId) {
        int result = adminService.deleteQnAAdnminVer(qnaId);
        if (result > 0) {
            return "QnA 삭제가 완료되었습니다.";
        }else {
            return "QnA 삭제에 실패하였습니다.";
        }
    }

    // TODO : QnA 선택 삭제

    @Operation(summary = "QnA 선택 삭제", description = "QnA 선택 삭제 answer가 존재해도 삭제 가능 (비활성화)")
    @PostMapping("/admin/qna/list/delete")
    public String deleteQnAListAdminVer(@RequestBody List<Integer> qnaIds) {
        int result = adminService.deleteQnAListAdminVer(qnaIds);
        if(result > 0) {
            return "QnA 삭제가 완료되었습니다.";
        }else {
            return "QnA 삭제에 실패하였습니다.";
        }
    }


    // TODO : QnA Answer 생성
    // TODO : QnA Answer 수정 : QnA Answer 생성과 같은 API 사용
    @Operation(summary = "QnA Answer 생성", description = "QnA Answer 생성 API ( Answer 수정 과 같이 사용 )")
    @PostMapping("/admin/qna/answer")
    public String setQnAAnswer(@RequestBody QnAAnswerDto qnAAnswerDto) {
      int result = adminService.setQnAAnswer(qnAAnswerDto);
      if(result > 0) {
          return "QnA 답변 생성이 완료되었습니다";
        }else {
          return "QnA 답변 생성에 실패하였습니다";
        }
    }


    // =================== 설문 =======================

    // TODO : 전체 설문 리스트 출력 : 비활성화 포함
    @Operation(summary = "전체 설문 리스트 출력", description = "전체 설문 리스트 출력 (비활성화 포함)")
    @GetMapping("/admin/survey/list")
    public HashMap<String, Object> getSurveyListAdminVer(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
    ) {
        return adminService.getSurveyListAdminVer(page, search, order);
    }

    // TODO : 설문 디테일 출력 : 마감전, 후 를 나누지 않고 항상 마감 후 를 가정하에 디테일 페이지가 출력되어야함
    // TODO : 설문 디테일 통계 1, 2 데이터 필요
    @Operation(summary = "설문 디테일 출력", description = "설문 디테일 출력 : 마감전, 후 를 나누지 않고 항상 마감 후 를 가정하에 디테일 페이지가 출력되어야함")
    @GetMapping("/admin/survey/detail")
    public HashMap<String, Object> getSurveyDetailAdminVer(@RequestParam(name = "surveyId") int surveyId) {
      return adminService.getSurveyDetailAdminVer(surveyId);
    }

    // TODO : 설문 삭제 admin ver
    @Operation(summary = "설문 삭제", description = "설문 삭제 (해당 설문을 생성한 계정이 비할성화 되어있어도 해당 설문지 삭제(비활성화) 가능 )")
    @PostMapping("/admin/survey/delete")
    public String deleteSurveyAdminVer(@RequestBody Integer surveyId) {
        int result = adminService.deleteSurveyAdminVer(surveyId);
        if(result > 0) {
            return "설문 삭제 완료";
        }
        return "설문 삭제 실패";
    }

    // TODO : 설문 선택 삭제 admin ver
    @Operation(summary = "설문 선택 삭제", description = "설문 선택 삭제 (관리자 버전)")
    @PostMapping("/admin/survey/delete/list")
    public String deleteSurveyListAdminVer(@RequestBody List<Integer> surveyIds) {
        int result = adminService.deleteSurveyListAdminVer(surveyIds);
        if (result > 0) {
            return "설문들이 삭제 완료되었습니다.";
        }
        return "설문들 삭제에 실패하였습니다.";
    }

}
