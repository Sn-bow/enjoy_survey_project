package com.enjoy.survey.happyLife.admin;


import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
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

    @Select("select * from user where username like #{search} order by id desc limit #{page}, 10")
    List<UserEntity> getUserList(String search, int page);

    @Select("select count(id) from user where username like #{search} order by id desc")
    int getUserListCount(String search);

    @Select("select * from user where id = #{userId}")
    UserEntity getUserInfoAdminVer(int userId);

}
