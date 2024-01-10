package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.admin.dto.QnAAnswerDto;
import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.inquiry.InquiryEntity;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.survey.SurveyEntity;
import com.enjoy.survey.happyLife.survey.dto.QuestionCountDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyDetailDto;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.dto.UserAttendSurveyDto;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("select * from board " +
            "where content like #{search} " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<BoardEntity> getBoardListAdminVer(int page, String search, String filter, String orderBy);

    @Select("select count(id) from board " +
            "where content like #{search}")
    int getBoardCountAdminVer(String search);

    @Select("select * from board where id = #{boardId}")
    BoardEntity getBoardDetailAdminVer(int boardId);

    @Select("select * from comment where board_id = #{boardId}")
    List<CommentEntity> getCommentListAdminVer(int boardId);

    @Select("select * from user where username like #{search} order by id desc limit #{page}, 10")
    List<UserEntity> getUserList(String search, int page);

    @Select("select count(id) from user where username like #{search} order by id desc")
    int getUserListCount(String search);

    @Select("select * from user where id = #{userId}")
    UserEntity getUserInfoAdminVer(int userId);

    // 회원탈퇴
    @Update("update user set delete_state = true where id = #{userId}")
    int leaveUserAdminVer(int userId);

    // select board where user
    @Select("select * from board " +
            "where content like #{search} and member_id = #{userId} " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<BoardEntity> getBoardListForUserAdminVer(int page, String search, String filter, String orderBy, int userId);

    @Select("select count(id) from board " +
            "where content like #{search} and member_id = #{userId}")
    int getBoardCountForUserAdminVer(String search, int userId);

    // comment
    // TODO : comment List 출력시에 페이징 처리를 해야할것 같음
    @Select("select * from comment " +
            "where member_id = #{userId} and content like #{search} " +
            "order by id desc " +
            "limit #{page} and 10")
    List<CommentEntity> getCommentListForUserAdminVer(int userId, String search ,int page);

    @Select("select count(*) from comment " +
            "where member_id = #{userId} and content like #{search}")
    int getCommentListForUserCountAdminVer(int userId, String search);


    // TODO: 1대1 설문에서도 페이징이 필요함
    @Select("select * from one_to_one_inquiry where member_id = #{userId}")
    List<InquiryEntity> getInquiryListAdminVer(int userId);

    // TODO: QnA에서도 페이징이 필요함
    @Select("select * from question_and_answer where member_id = #{userId}")
    List<QnAEntity> getQnAListForUserAdminVer(int userId);


    // survey
    @Select("select * from survey " +
            "where survey_content like #{search} and member_id = #{userId}" +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<SurveyEntity> getSurveyListForUserAdminVer(int page, String search, String filter, String orderBy, int userId);

    @Select("select count(id) from survey " +
            "where survey_content like #{search} and member_id = #{userId}")
    int getSurveyListCountForUserAdminVer(String search, int userId);

    @Select("select " +
            "p.id p_id, p.sq_id, p.member_id, rs.id sur_id, " +
            "rs.topic_id, rs.survey_content, rs.member_id, " +
            "rs.end_date, rs.hit, rs.delete_state, rs.alarm_state " +
            "from " +
            "( select * from participate_in_the_survey where member_id = #{userId}) p " +
            "join ( " +
            "select s.*, sq.question, sq.id sq_id " +
            "from " +
            "survey s join survey_question sq on s.id = sq.survey_id ) rs " +
            "on p.sq_id = rs.sq_id " +
            "where survey_content like #{search} " +
            "ORDER BY p_id desc limit #{page}, 10")
    List<UserAttendSurveyDto> getSurveyAttendListForUserAdminVer(int userId, String search, int page);

    @Select("select " +
            "count(p.id) " +
            "from " +
            "( " +
            "select " +
            "* " +
            "from " +
            "participate_in_the_survey " +
            "where " +
            "member_id = #{userId}) p " +
            "join ( " +
            "select " +
            "s.*, sq.question, sq.id sq_id " +
            "from " +
            "survey s " +
            "join " +
            "survey_question sq " +
            "on " +
            "s.id = sq.survey_id) rs " +
            "on " +
            "p.sq_id = rs.sq_id " +
            "where survey_content like #{search}")
    int getSurveyAttendCountAdminVer(int userId, String search);

    @Select("select * from one_to_one_inquiry " +
            "where title like #{search} order by ${filter} ${orderBy} limit #{page}, 10")
    List<InquiryEntity> getInquiryAllListAdminVer(String search, String filter, String orderyBy, int page);

    @Select("select count(*) from one_to_one_inquiry " +
            "where title like #{search}")
    int getInquiryAllListCountAdminVer(String search);

    @Update("update one_to_one_inquiry set answer = #{answer} where id = #{inquiry_id}")
    int setInquiryAnswer(InquiryAnswerRegDto inquiryAnswerRegDto);

    // TODO: QnA에서도 페이징이 필요함
    @Select("select * from question_and_answer where title like #{search} order by ${filter} ${orderBy} limit #{page}, 10")
    List<QnAEntity> getQnAAllListAdminVer(String search, String filter, String orderBy, int page);

    @Select("select coount(*) from question_and_answer where title like #{search}")
    int getQnAAllListCountAdminVer(String search);

    @Update("update one_to_one_inquiry set delete_state = true where id = #{inquiryId}")
    int deleteInquiryAdminVer(int inquiryId);


    @Update("update question_and_answer set answer = #{answer} where id = #{qnaId}")
    int setQnAAnswer(QnAAnswerDto qnAAnswerDto);

    // 설문

    @Select("select * from survey " +
            "where survey_content like #{search} " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<SurveyEntity> getSurveyListAdminVer(int page, String search, String filter, String orderBy);

    @Select("select count(id) from survey " +
            "where survey_content like #{search}")
    int getSurveyCountAdminVer(String search);



    // admin version으로 delete_state = true여도 보여져야함 설문 디테일 페이지 중 설문 참여를 위한 쿼리문 set1, set2 : 주제, 내용, 사진, 만료시간, 시작시간, 참여수
    @Select("select su.*, t.topic_name " +
            "from (select s.*, p.orgNm, p.savedNm, p.savedPath from survey s " +
            "left join (select orgNm, savedNm, savedPath, survey_id from survey_picture) p " +
            "on s.id = p.survey_id where id = #{surveyId}) su " +
            "join topic t on su.topic_id = t.id")
    SurveyDetailDto getSurveyDetail1AdminVer(int surveyId);

    // 설문 디테일 페이지 마감시간이 다되었을경우 각 질문들의 카운트 출력에 필요한 쿼리 set2
    @Select("SELECT " +
            "    q.id, " +
            "    q.question, " +
            "    q.survey_id, " +
            "    COUNT(p.id) AS participation_count " +
            "FROM " +
            "    survey_question q " +
            "LEFT JOIN " +
            "    participate_in_the_survey p ON q.id = p.sq_id " +
            "WHERE " +
            "    q.survey_id = #{surveyId} " +
            "GROUP BY " +
            "    q.id, q.question, q.survey_id")
    List<QuestionCountDto> getSurveyEndDetail1(int surveyId);

    // TODO : 설문 디테일 페이지 마감시간이 다 되었을 경우 각 질문들의 남, 여 비율 (%) 쿼리 set2
    void getSurveyEndDetail2(int surveyId);

    // TODO : 설문 디테일 페이지 마감시간이 다 되었을 경우 각 질문들의 10대, 20대, 30대, 기타 비율 (%) 쿼리 set2
    void getSurveyEndDetail3(int surveyId);

    @Update("update survey set delete_state = true where id = #{surveyId}")
    int deleteSurveyAdminVer(int surveyId);


    // 댓글
    @Update("update comment set delete_state = true where id = #{commentId}")
    int deleteCommentAdminVer(int commentId);


    @Update("update board set delete_state = true where id = #{boardId}")
    int deleteBoardAdminVer(int boardId);

}
