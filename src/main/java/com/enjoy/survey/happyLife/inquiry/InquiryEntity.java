package com.enjoy.survey.happyLife.inquiry;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class InquiryEntity {

    int id;
    String question;
    String answer;
    int member_id;
    Timestamp create_date;
    boolean delete_state;
    boolean alarm_state;

}
