package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.inquiry.InquiryEntity;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.survey.SurveyEntity;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.dto.UserAttendSurveyDto;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
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


}
