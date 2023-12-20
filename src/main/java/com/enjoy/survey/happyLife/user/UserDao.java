package com.enjoy.survey.happyLife.user;



import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.survey.SurveyEntity;
import com.enjoy.survey.happyLife.user.dto.UserAttendSurveyDto;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import com.enjoy.survey.happyLife.user.dto.UserSimpleInfoDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDao {

    @Select("select * from user where username = #{username}")
    UserEntity findByUsername(String username);

    @Select("select delete_state from user where username = #{username}")
    boolean findByDeleteState(String username);

    @Insert("insert into user(email, username,name, password, role, birth, gender) values(#{email}, #{username},#{name}, #{password}, #{role}, #{birth}, #{gender})")
    int signUp(UserSignUpDto user);

    @Insert("insert into areas_of_interest(member_id, topic_id) values(#{member_id}, #{topic_id})")
    void saveSelectedTopic(int member_id, int topic_id);

    @Select("select id, username, name, email, birth, gender, create_date, delete_state from user where username = #{username} and delete_state = false")
    UserInfoDto getUserInfo(String username);

    @Select("select id, username, name, email from user where username = #{username} and delete_state = false")
    UserSimpleInfoDto getUserSimpleInfo(String username);

    // select board where user
    @Select("select * from board " +
            "where content like #{search} and delete_state = false and member_id = #{userId} " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<BoardEntity> getBoardListForUser(int page, String search, String filter, String orderBy, int userId);

    @Select("select count(id) from board " +
            "where content like #{search} and delete_state = false and member_id = #{userId}")
    int getBoardCountForUser(String search, int userId);

    // comment
    // TODO : comment List 출력시에 페이징 처리를 해야할것 같음
    @Select("select * from comment " +
            "where delete_state = false and member_id = #{userId}")
    List<CommentEntity> getCommentListForUser(int userId);


    // TODO: QnA에서도 페이징이 필요함
    @Select("select * from question_and_answer where member_id = #{userId}")
    List<QnAEntity> getQnAListForUser(int userId);

    @Select("select * from survey " +
            "where survey_content like #{search} and delete_state = false and member_id = #{userId}" +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<SurveyEntity> getSurveyListForUser(int page, String search, String filter, String orderBy, int userId);


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
            "where survey_content like #{search} and delete_state = false " +
            "ORDER BY p_id desc limit #{page}, 10")
    List<UserAttendSurveyDto> getSurveyAttendListForUser(int userId, String search, int page);

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
            "where survey_content like #{search} and delete_state = false")
    int getSurveyAttendCount(int userId, String search);

    // 회원탈퇴
    @Update("update user set delete_state = true where username = #{username}")
    int leaveUser(String username);


}
