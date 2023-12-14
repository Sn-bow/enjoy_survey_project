package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SurveyDao {

    @Select("select * from survey")
    List<SurveyEntity> getSurveyList();

    @Insert("insert into survey(topic_id, survey_content, member_id, end_date)" +
            " values(#{topic_id}, #{survey_content}, #{member_id}, #{end_date})")
    int setSurvey(SurveyRegDto surveyRegDto);

    @Select("select id from survey where member_id = #{member_id} and id = (select MAX(id) from survey)")
    int getSurveyId(int member_id);

    @Insert("insert into survey_picture(orgNm, savedNm, savedPath, survey_id) values(#{orgNm}, #{savedNm}, #{savedPath}, #{survey_id})")
    int setPicture(SurveyPictureEntity picture);

    @Insert("insert into survey_question(question, survey_id) values(#{question}, #{survey_id})")
    int setQuestion(String question, int survey_id);
}
