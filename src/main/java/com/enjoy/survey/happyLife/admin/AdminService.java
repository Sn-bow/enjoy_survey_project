package com.enjoy.survey.happyLife.admin;

import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.board.BoardService;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.inquiry.InquiryService;
import com.enjoy.survey.happyLife.qna.QnAService;
import com.enjoy.survey.happyLife.survey.SurveyService;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserService userService;
    private final SurveyService surveyService;
    private final BoardService boardService;
    private final InquiryService inquiryService;
    private final QnAService qnAService;
    private final AdminDao adminDao;

    // ============== 유저 ====================
    // TODO : 유저 삭제 (= 비활성화)
    public void deleteUser() {
    }

    // TODO : 유저 리스트 출력
    public HashMap<String, Object> getUserList(String search, int page) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        List<UserEntity> userEntityList = adminDao.getUserList(rSearch, rPage);
        int userEntityListCount = adminDao.getUserListCount(rSearch);
        HashMap<String, Object> data = new HashMap<>();
        data.put("userEntityList", userEntityList);
        data.put("userEntityListCount", userEntityListCount);
        return data;
    }

    // TODO : 유저 디테일 출력
    public UserEntity getUserDetailAdminVer(int userId) {
        return adminDao.getUserInfoAdminVer(userId);
    }

    // TODO : 유저 선택 삭제 (= 비활성화)
    public void temp4() {}

    // ================= 유저 & 게시물, 댓글 & 1대1 문의, QnA & 설문 ===========

    // TODO : 유저가 작성한 게시물 리스트 출력
    public void temp5() {
    }

    // TODO : 유저가 작성한 댓글 리스트 출력
    public void temp6() {}

    // TODO : 유저가 작성한 1 대 1 리스트 문의 출력
    public void temp7() {}

    // TODO : 유저가 작성한 QnA 리스트 출력
    public void temp8() {}

    // TODO : 유저가 작성한 설문 리스트 출력
    public void temp9() {}

    // TODO : 유저가 참여한 설문 리스트 출력
    public void temp10() {}

    // =============== 게시물, 댓글 ====================
    public HashMap<String, Object> getBoardListAdminVer(int page, String search, String order) {
        // 삭제(=비활성화) 된 게시물도 보이게됨
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        OrderSwitch orderSwitch = new OrderSwitch();
        List<BoardEntity> boardList = adminDao.getBoardListAdminVer(rPage, rSearch, orderSwitch.switching(order).get(0), orderSwitch.switching(order).get(1));
        int count = adminDao.getBoardCountAdminVer(rSearch);
        HashMap<String, Object> boardListAndCount = new HashMap<>();
        boardListAndCount.put("boardList", boardList);
        boardListAndCount.put("count", count);
        return boardListAndCount;
    }

    public HashMap<String, Object> getBoardDetailAdminVer(int boardId) throws Exception {
        // 삭제(=비활성화) 된 게시물도 보이게됨
        BoardEntity board = adminDao.getBoardDetailAdminVer(boardId);
        List<CommentEntity> commentList = adminDao.getCommentListAdminVer(boardId);
        HashMap<String, Object> boardDetailAndComments = new HashMap<>();
        boardDetailAndComments.put("boardDetail", board);
        boardDetailAndComments.put("commentList", commentList);
        if (board != null) {
            return boardDetailAndComments;
        }else {
            throw new Exception("board가 존재하지 않습니다.");
        }
    }

    // ================== 1대1 문의, QnA =================

    // TODO : 전체 1대1 문의 리스트 출력
    public void temp17() {}

    // TODO : 1대1 문의 디테일 출력
    public void temp18() {}

    // TODO : 전체 QnA 리스트 출력
    public void temp19() {}

    // TODO : QnA 디테일 출력
    public void temp20() {}

    // TODO : 1대1 문의 삭제
    public void temp21() {}

    // TODO : 1대1 문의 선택 삭제
    public void temp22() {}

    // TODO : 1대1 문의 답변 생성
    public void temp23() {}

    // TODO : 1대1 문의 답변 수정
    public void temp24() {}

    // TODO : QnA 삭제
    public void temp25() {}

    // TODO : QnA 선택 삭제
    public void temp26() {}

    // TODO : QnA Answer 생성
    public void temp27() {}

    // TODO : QnA Answer 수정
    public void temp28() {}

    // =================== 설문 =======================

    // TODO : 전체 설문 리스트 출력
    public void temp29() {}

    // TODO : 설문 디테일 출력
    public void temp30() {}

    // TODO : 설문 삭제
    public void temp31() {}

    // TODO : 설문 선택 삭제
    public void temp32() {}

}
