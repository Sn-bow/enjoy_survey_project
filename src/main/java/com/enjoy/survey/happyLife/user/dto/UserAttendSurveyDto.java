package com.enjoy.survey.happyLife.user.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserAttendSurveyDto {
    // 참여테이블 아이디
    int p_id;
    // 내가 선택한 선택지 아이디
    int sq_id;
    // 내가 선택한 선택지 내용
    String question;
    // 내(user) 아이디
    int a_member_id;
    // 설문 아이디
    int sur_id;
    // topic_id
    int topic_id;
    // 설문 내용
    String survey_content;
    // 생성한 유저 아이디
    int c_member_id;
    // 마감 시간
    Timestamp end_date;
    // 참여자 수
    int hit;
    // 삭제 상태
    boolean delete_state;
    // 알림 상태
    boolean alarm_state;
}
