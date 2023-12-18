package com.enjoy.survey.happyLife.survey.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class SurveyDetailDto {
    // 설문 아이디
    int id;
    String survey_content;
    Timestamp start_date;
    Timestamp end_date;
    int hit;
    boolean delete_state;
    boolean alarm_state;
    String orgNm;
    String savedNm;
    String savedPath;
    String topic_name;
}
