package com.enjoy.survey.happyLife.admin;

import com.enjoy.survey.happyLife.admin.dto.QnAAnswerDto;
import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.board.BoardService;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.inquiry.InquiryEntity;
import com.enjoy.survey.happyLife.inquiry.InquiryService;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.qna.QnAService;
import com.enjoy.survey.happyLife.survey.SurveyDao;
import com.enjoy.survey.happyLife.survey.SurveyEntity;
import com.enjoy.survey.happyLife.survey.SurveyService;
import com.enjoy.survey.happyLife.survey.dto.QuestionCountDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyDetailDto;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.UserService;
import com.enjoy.survey.happyLife.user.dto.UserAttendSurveyDto;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final SurveyDao surveyDao;
    private final UserDao userDao;

    // ============== 유저 ====================
    // TODO : User Info Modify
    public int userInfoModify(UserSignUpDto user) {
        UserEntity userBeforeInfo = userDao.findByUsername(user.getUsername());

        String email = user.getEmail().isEmpty() ? userBeforeInfo.getEmail() : user.getEmail();
        String name = user.getName().isEmpty() ? userBeforeInfo.getName() : user.getName();
        String birth = user.getBirth().isEmpty() ? userBeforeInfo.getBirth() : user.getBirth();
        String gender = user.getGender().isEmpty() ? userBeforeInfo.getGender() : user.getGender();

        // username = 선택한 사용자 유저 네임
        int result = userDao.userInfoModify(email, name, birth, gender, user.getUsername());
        return result;
    }


    // TODO : 유저 삭제 (= 비활성화)
    public int deleteUser(int userId) {
        return adminDao.leaveUserAdminVer(userId);
    }

    // TODO : 유저 선택 삭제 (= 비활성화)
    public int choiceDeleteUser(List<Integer> userIds) {
        int result = 0;
        for(int userId : userIds) {
            result = adminDao.leaveUserAdminVer(userId);
            if(result == 0) {
                break;
            }
        }
        return result;
    }

    // TODO : 유저 리스트 출력
    // TODO : order 수정 필요
    public HashMap<String, Object> getUserList(String search, int page) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        // TODO : UserList paging order 부분 수정 필요
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



    // ================= 유저 & 게시물, 댓글 & 1대1 문의, QnA & 설문 ===========

    // TODO : 유저가 작성한 게시물 리스트 출력
    // TODO : order 수정 필요
    public HashMap<String, Object> getBoardListAdminVer(int page, String search, String order, int userId) {

        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        // 오더 부분 수정 필요 -> 필터 와 같은 타이틀 수정이 필요함
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "게시판");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        List<BoardEntity> boardList = adminDao.getBoardListForUserAdminVer(rPage, rSearch, filter, orderBy, userId);
        int count = adminDao.getBoardCountForUserAdminVer(rSearch, userId);

        HashMap<String, Object> boardListAndCount = new HashMap<>();
        boardListAndCount.put("boardList", boardList);
        boardListAndCount.put("count", count);

        return boardListAndCount;
    }

    // TODO : 유저가 작성한 댓글 리스트 출력
    // TODO : order 수정 필요
    public HashMap<String, Object> getCommentListAdminVer(String search, int userId, int page) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        // TODO : cmtList Paging 처리 필요
        List<CommentEntity> cmtList = adminDao.getCommentListForUserAdminVer(userId, rSearch, rPage);
        int cmtListCount = adminDao.getCommentListForUserCountAdminVer(userId, rSearch);

        HashMap<String, Object> cmtListAndCount = new HashMap<>();
        cmtListAndCount.put("cmtList", cmtList);
        cmtListAndCount.put("count", cmtListCount);

        return cmtListAndCount;
    }

    // TODO : 유저가 작성한 1 대 1 리스트 문의 출력
    public List<InquiryEntity> getInquiryListAdminVer(int userId) {
        // TODO : 리스트 페이징 처리 작업 필요
        return adminDao.getInquiryListAdminVer(userId);
    }

    // TODO : 유저가 작성한 QnA 리스트 출력
    public List<QnAEntity> getQnAListAdminVer(int userId) {
        // TODO : 리스트 페이징 처리 작업 필요
        return adminDao.getQnAListForUserAdminVer(userId);
    }

    // TODO : 유저가 작성한 설문 리스트 출력
    public HashMap<String, Object> getSurveyListAdminVer(int page, String search, String order, int userId) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "설문");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        // TODO : count 만들어야함
        int count = adminDao.getSurveyListCountForUserAdminVer(rSearch, userId);
        List<SurveyEntity> surveyList = adminDao.getSurveyListForUserAdminVer(rPage, rSearch, filter, orderBy, userId);

        HashMap<String, Object> data = new HashMap<>();
        data.put("surveyList", surveyList);
        data.put("count", count);

        return data;
    }

    // TODO : 유저가 참여한 설문 리스트 출력
    public HashMap<String, Object> getAttendSurveyListAdminVer(int userId, String search, int page) {

        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        // TODO : 참여한 설문 리스트 페이징 처리 필요
        List<UserAttendSurveyDto> userAttendSurveyDtos = adminDao.getSurveyAttendListForUserAdminVer(userId, rSearch, rPage);
        int userAttendSurveyListCount = adminDao.getSurveyAttendCountAdminVer(userId, rSearch);

        HashMap<String, Object> attendSuvAndCount = new HashMap<>();
        attendSuvAndCount.put("atdSurveyList", userAttendSurveyDtos);
        attendSuvAndCount.put("atdSvListCount", userAttendSurveyListCount);

        return attendSuvAndCount;
    }

    // =============== 게시물, 댓글 ====================
    public HashMap<String, Object> getBoardListAdminVer(int page, String search, String order) {
        // 삭제(=비활성화) 된 게시물도 보이게됨
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "게시판");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        HashMap<String, Object> boardListAndCount = new HashMap<>();

        List<BoardEntity> boardList = adminDao.getBoardListAdminVer(rPage, rSearch, filter, orderBy);
        int count = adminDao.getBoardCountAdminVer(rSearch);

        boardListAndCount.put("boardList", boardList);
        boardListAndCount.put("count", count);

        return boardListAndCount;
    }

    public HashMap<String, Object> getBoardDetailAdminVer(int boardId) throws Exception {
        // 삭제(=비활성화) 된 게시물도 보이게됨
        BoardEntity board = adminDao.getBoardDetailAdminVer(boardId);
        // TODO : 댓글 페이징 처리 부분 필요
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

    public int deleteBoardAdminVer(int boardId) {
        return adminDao.deleteBoardAdminVer(boardId);
    }

    public int choiceDeleteBoardAdminVer(List<Integer> boardIds) {
        int result = 0;
        for (int boardId : boardIds) {
            result = adminDao.deleteBoardAdminVer(boardId);
            if(result == 0){
                break;
            }
        }
        return result;
    }

    public int deleteCommentAdminVer(int cmtId) {
        return adminDao.deleteCommentAdminVer(cmtId);
    }

    public int deleteCommentListAdminVer(List<Integer> cmtIds) {
        int result = 0;
        for(int cmtId : cmtIds) {
            result = adminDao.deleteCommentAdminVer(cmtId);
            if (result == 0) {
                break;
            }
        }
        return result;
    }

    // ================== 1대1 문의, QnA =================

    public int setInquiryAnswer(InquiryAnswerRegDto inquiryAnswerRegDto) {
        return adminDao.setInquiryAnswer(inquiryAnswerRegDto);
    }

    // TODO : 전체 1대1 문의 리스트 출력
    public HashMap<String, Object> getInquiryListAdminVer(String search, int page, String order) {

        String rSearch = "%" + search + "%";
        int rPage = (page - 1) * 10;

        // TODO : search 의 경우 param 을 추가해서 title 만이 아니라 다른것들도 선택해서 검색할 수 있게 하면 좋을것같음
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "1대1문의");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");


        List<InquiryEntity> inquiryEntityAllList = adminDao.getInquiryAllListAdminVer(rSearch, filter, orderBy, rPage);
        int inquiryEntityAllListCount = adminDao.getInquiryAllListCountAdminVer(rSearch);

        HashMap<String, Object> data = new HashMap<>();
        data.put("inqAllList", inquiryEntityAllList);
        data.put("inqAllListCount", inquiryEntityAllListCount);

        return data;
    }

    public InquiryEntity getInquiryAdminVer(int inquiryId) {
        return adminDao.getInquiryAdminVer(inquiryId);
    }

    // TODO : 전체 QnA 리스트 출력
    public HashMap<String, Object> getQnAListAdminVer(String search, String order, int page) {
        String rSearch = "%" + search + "%";
        int rPage = (page - 1) * 10;

        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "QnA");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        List<QnAEntity> qnAEntityAllList = adminDao.getQnAAllListAdminVer(rSearch, filter, orderBy, rPage);
        int qnAEntityAllListCount = adminDao.getQnAAllListCountAdminVer(rSearch);

        HashMap<String, Object> data = new HashMap<>();
        data.put("QnAAllList", qnAEntityAllList);
        data.put("QnAAllListCount", qnAEntityAllListCount);

        return data;
    }

    // TODO : 1대1 문의 삭제
    public int deleteInquiryAdminVer(int inquiryId) {
        return adminDao.deleteInquiryAdminVer(inquiryId);
    }

    // TODO : 1대1 문의 선택 삭제
    public int listDeleteInquiryAdminVer(List<Integer> inquiryIds) {
        int result = 0;
        for (int inquiryId : inquiryIds) {
            result = adminDao.deleteInquiryAdminVer(inquiryId);
            if (result == 0) {
                break;
            }
        }
        return result;
    }

    // TODO : QnA 삭제
    public int deleteQnAAdnminVer(int qnaId) {
        return qnAService.deleteQnA(qnaId);
    }

    // TODO : QnA 선택 삭제
    public int deleteQnAListAdminVer(List<Integer> qnaIds) {
        int result = 0;
        for (int qnaId : qnaIds) {
            result = qnAService.deleteQnA(qnaId);
            if (result == 0) {
                break;
            }
        }
        return result;
    }

    // TODO : QnA Answer 생성
    public int setQnAAnswer(QnAAnswerDto qnAAnswerDto) {
        return adminDao.setQnAAnswer(qnAAnswerDto);
    }

    // =================== 설문 =======================

    // TODO : 전체 설문 리스트 출력
    public HashMap<String, Object> getSurveyListAdminVer(int page, String search, String order) {
        int rPage = (page -1 ) * 10;
        String rSearch = "%" + search + "%";
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "설문");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        List<SurveyEntity> surveyListAdminVer = adminDao.getSurveyListAdminVer(rPage, rSearch, filter, orderBy);
        int surveyListAdminVerCount = adminDao.getSurveyCountAdminVer(search);

        HashMap<String, Object> data = new HashMap<>();
        data.put("surveyList", surveyListAdminVer);
        data.put("surveyListCount", surveyListAdminVerCount);

        return data;

    }

    // TODO : 설문 디테일 출력 :
    public HashMap<String, Object> getSurveyDetailAdminVer(int surveyId) {
        SurveyDetailDto surveyDetailDto = adminDao.getSurveyDetail1AdminVer(surveyId);
        List<QuestionCountDto> questionCountDtoList = surveyDao.getSurveyEndDetail1(surveyId);
//        surveyDao.getSurveyDetail1(surveyId);
//        surveyDao.getSurveyDetail1(surveyId);
        // TODO : 설문 디테일 출력시 통계부분 만들어서 추가해주어야함
        HashMap<String, Object> data = new HashMap<>();
        data.put("surveyDetail", surveyDetailDto);
        data.put("questionCount", questionCountDtoList);
        return data;
    }

    // TODO : 설문 삭제
    public int deleteSurveyAdminVer(int surveyId) {
        // admin의 경우 계정이 비할성화 되어있어도 삭제(비활성화)가 가능
        return adminDao.deleteSurveyAdminVer(surveyId);
    }

    // TODO : 설문 선택 삭제
    public int deleteSurveyListAdminVer(List<Integer> surveyIds) {
        int result = 0;
        for (int surveyId : surveyIds) {
            result = adminDao.deleteSurveyAdminVer(surveyId);
            if (result == 0) {
                break;
            }
        }
        return result;
    }

}
