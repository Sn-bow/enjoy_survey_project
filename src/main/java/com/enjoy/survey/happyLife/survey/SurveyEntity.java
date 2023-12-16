package com.enjoy.survey.happyLife.survey;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class SurveyEntity {

    int id;
    int topic_id;
    String survey_content;
    int member_id;
    Timestamp start_date;
    Timestamp end_date;
    int hit;
    boolean delete_state;
    boolean alarm_state;

}
