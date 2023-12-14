package com.enjoy.survey.happyLife.survey.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
public class SurveyRegDto {

    // survey
    int topic_id;
    String survey_content;
    int member_id;
    Timestamp end_date;

    // picture
    MultipartFile file;
    int survey_id;
}
