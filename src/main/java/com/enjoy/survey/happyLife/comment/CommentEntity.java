package com.enjoy.survey.happyLife.comment;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentEntity {

    int id;
    String content;
    Timestamp reg_date;
    int board_id;
    boolean delete_state;
    int member_id;

}
