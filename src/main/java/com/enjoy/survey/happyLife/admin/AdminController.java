package com.enjoy.survey.happyLife.admin;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ============== 유저 ====================
    // TODO : 유저 삭제 (= 비활성화)
    @Operation(summary = "관리자 계정에서 사용자들 계정 비활성화 API", description = "관리자 계정에서 사용자들 계정 비활성화 API")
    @PostMapping("/admin/user/delete")
    public void deleteUser() {

    }

//    // TODO : 유저 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp2() {}
//
//    // TODO : 유저 디테일 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp3() {}
//
//    // TODO : 유저 선택 삭제 (= 비활성화)
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp4() {}
//
//    // ================= 유저 & 게시물, 댓글 & 1대1 문의, QnA & 설문 ===========
//
//    // TODO : 유저가 작성한 게시물 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp5() {}
//
//    // TODO : 유저가 작성한 댓글 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp6() {}
//
//    // TODO : 유저가 작성한 1 대 1 리스트 문의 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp7() {}
//
//    // TODO : 유저가 작성한 QnA 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp8() {}
//
//    // TODO : 유저가 작성한 설문 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp9() {}
//
//    // TODO : 유저가 참여한 설문 리스트 출력
//    @Operation(summary = "", description = "")
//    @PostMapping("")
//    public void temp10() {}

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
