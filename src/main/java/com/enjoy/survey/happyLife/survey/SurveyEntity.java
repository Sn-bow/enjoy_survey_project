package com.enjoy.survey.happyLife.survey;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class SurveyEntity {

    @NotNull(message = "Id cannot be null")
    @Schema(description = "ID")
    int id;

    @NotNull(message = "Topic id cannot be null")
    @Schema(description = "설문 주제", example = "3")
    int topic_id;

    @NotNull(message = "survey_content cannot be null")
    @Schema(description = "설문 내용", example = "카페에 가면 주로 무엇을 주문 하나요?")
    String survey_content;

    @NotNull(message = "member_id cannot be null")
    @Schema(description = "설문 작성자", example = "1")
    int member_id;

    @NotNull(message = "start_date cannot be null")
    @Schema(description = "설문 시작 날짜 : 설문 생성시 서버 시간으로 자동 생성", example = "쿼리문 자동 생성", defaultValue = "now()")
    Timestamp start_date;

    @NotNull(message = "end_date cannot be null")
    @Schema(description = "설문 끝 날짜 : ex) 2023-12-25 00:00:00", example = "2023-12-25 00:00:00")
    Timestamp end_date;

    @NotNull(message = "hit cannot be null")
    @Schema(description = "다른 사용자들의 설문 참여 횟수", example = "참여시 자동 카운트", defaultValue = "0")
    int hit;

    @NotNull(message = "delete_state cannot be null")
    @Schema(description = "설문 삭제 상태 : 회원 탈퇴 및 회원 강퇴시 자동 변환", defaultValue = "false")
    boolean delete_state;

    @NotNull(message = "Topic id cannot be null")
    @Schema(description = "설문 알림 상태 : 설문 등록한 사용자가 확인시 자동 변환", defaultValue = "false")
    boolean alarm_state;

}
