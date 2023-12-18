package com.enjoy.survey.happyLife.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardRegDto {

    // boardId
    String title;
    String content;
    int member_id;
    Timestamp modify_date;
    // 후에 공지사항 입력 폼과 같이 사용하기위해 남겨둠
    boolean notice_auth_state;

}
