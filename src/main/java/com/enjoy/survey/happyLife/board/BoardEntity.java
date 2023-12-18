package com.enjoy.survey.happyLife.board;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardEntity {

    int id;
    String title;
    String content;
    int hit;
    Timestamp reg_date;
    int member_id;
    boolean delete_state;
    Timestamp modify_date;
    // admin 권한을 가진 관리자가 게시글 작성시 공지사항으로 입력되며 최상위에 표시하기 위해서 존재하는 컬럼
    boolean notice_auth_state;

}
