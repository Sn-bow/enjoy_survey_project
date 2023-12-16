package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SurveyDao {

    @Select("select * from survey where survey_content like #{search} ORDER BY #{filter} ${orderBy} LIMIT #{page}, 10")
    List<SurveyEntity> getSurveyList(int page, String search, String filter, String orderBy);

    @Select("select * from survey where id = #{surveyId}")
    SurveyEntity getSurvey(int surveyId);

    @Insert("insert into survey(topic_id, survey_content, member_id, end_date)" +
            " values(#{topic_id}, #{survey_content}, #{member_id}, #{end_date})")
    int setSurvey(SurveyRegDto surveyRegDto);

    @Select("select id from survey where member_id = #{member_id} and id = (select MAX(id) from survey)")
    int getSurveyId(int member_id);

    @Insert("insert into survey_picture(orgNm, savedNm, savedPath, survey_id) values(#{orgNm}, #{savedNm}, #{savedPath}, #{survey_id})")
    int setPicture(SurveyPictureEntity picture);

    @Insert("insert into survey_question(question, survey_id) values(#{question}, #{survey_id})")
    int setQuestion(String question, int survey_id);

    @Delete("delete from survey where id = #{surveyId} and member_id = #{userId} ")
    int deleteSurvey(int surveyId, int userId);

    @Delete("delete from survey_question where survey_id = #{surveyId}")
    int deleteSurveyQuestion(int surveyId);

    @Delete("delete from survey_picture where survey_id = #{surveyId}")
    int deleteSurveyPicture(int surveyId);
}
