package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminDao {

    @Select("select * from board " +
            "where content like #{search} " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<BoardEntity> getBoardListAdminVer(int page, String search, String filter, String orderBy);

    @Select("select count(id) from board " +
            "where content like #{search}")
    int getBoardCountAdminVer(String search);

    @Select("select * from board where id = #{boardId}")
    BoardEntity getBoardDetailAdminVer(int boardId);

    @Select("select * from comment where board_id = #{boardId}")
    List<CommentEntity> getCommentListAdminVer(int boardId);

}
