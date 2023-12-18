package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.survey.dto.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SurveyDao {

    @Select("select * from survey " +
            "where survey_content like #{search} and delete_state = false " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<SurveyEntity> getSurveyList(int page, String search, String filter, String orderBy);

    @Select("select count(id) from survey " +
            "where survey_content like #{search} and delete_state = false")
    int getSurveyCount(String search);

    // 설문 디테일 페이지 중 설문 참여를 위한 쿼리문 set1, set2 : 주제, 내용, 사진, 만료시간, 시작시간, 참여수
    @Select("select su.*, t.topic_name " +
            "from (select s.*, p.orgNm, p.savedNm, p.savedPath from survey s " +
            "left join (select orgNm, savedNm, savedPath, survey_id from survey_picture) p " +
            "on s.id = p.survey_id where id = #{surveyId}) su " +
            "join topic t on su.topic_id = t.id where delete_state = false")
    SurveyDetailDto getSurveyDetail1(int surveyId);

    // 설문 디테일 페이지 중 설문 참여를 위한 선택지를 생성하는 쿼리문 set1 : 설문 선택지
    @Select("select * from survey_question where survey_id = #{surveyId}")
    List<QuestionDto> getSurveyDetail2(int surveyId);


    // 설문 디테일 페이지를 이미 참여하였을 경우 참여가 아닌 수정을 하기위한 조건문을 걸어주기 위한 쿼리 set1 : 설문 참여 유저 아이디
    @Select("select p.id, sq_id, member_id, question, survey_id from participate_in_the_survey p join survey_question q on p.sq_id = q.id where survey_id = #{surveyId}")
    List<AlreadyAttendSurveyUserDto> getSurveyDetail3(int surveyId);


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

    @Insert("insert into survey(topic_id, survey_content, member_id, end_date)" +
            " values(#{topic_id}, #{survey_content}, #{member_id}, #{end_date})")
    int setSurvey(SurveyRegDto surveyRegDto);

    @Select("select id from survey where member_id = #{member_id} and id = (select MAX(id) from survey)")
    int getSurveyId(int member_id);

    @Insert("insert into survey_picture(orgNm, savedNm, savedPath, survey_id) values(#{orgNm}, #{savedNm}, #{savedPath}, #{survey_id})")
    int setPicture(SurveyPictureEntity picture);

    @Insert("insert into survey_question(question, survey_id) values(#{question}, #{survey_id})")
    int setQuestion(String question, int survey_id);

    // TODO : 해당 부분 쿼리를 update로 delete_state 속성 값을 true로 변경해야함
    @Delete("delete from survey where id = #{surveyId} and member_id = #{userId} ")
    int deleteSurvey(int surveyId, int userId);

    @Delete("delete from survey_question where survey_id = #{surveyId}")
    int deleteSurveyQuestion(int surveyId);

    @Delete("delete from survey_picture where survey_id = #{surveyId}")
    int deleteSurveyPicture(int surveyId);

    // 설문 참여
    @Insert("insert into participate_in_the_survey(sq_id, member_id) values(#{sq_id}, #{user_id})")
    int attendSurvey(int sq_id, int user_id);

    // TODO : 설문 참여시 설문 참여인원 업데이트
    @Update("update survey set hit = #{hit} where id = #{surveyId}")
    int updateSurveyHit(int hit, int surveyId);

    @Update("update participate_in_the_survey set sq_id = #{sq_id} where member_id = #{user_id}")
    int attendSurveyUpdate(int sq_id, int user_id);
}
