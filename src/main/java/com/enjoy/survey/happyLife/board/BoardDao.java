package com.enjoy.survey.happyLife.board;


import com.enjoy.survey.happyLife.board.dto.BoardModifyFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardRegDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardDao {

    @Select("select * from board " +
            "where content like #{search} and delete_state = false " +
            "ORDER BY ${filter} ${orderBy} LIMIT #{page}, 10")
    List<BoardEntity> getBoardList(int page, String search, String filter, String orderBy);

    @Select("select count(id) from board " +
            "where content like #{search} and delete_state = false")
    int getBoardCount(String search);

    @Select("select max(id) from board where member_id = #{userId} and delete_state = false order by id desc")
    int getNewBoardId(int userId);

    @Select("select * from board where id = #{boardId} and delete_state = false")
    BoardEntity getBoardDetail(int boardId);

    @Select("select member_id from board where id = #{boardId}")
    BoardRegDto getSimpleBoardInfo(int boardId);

    @Update("update board set hit = #{hit} where id = #{boardId}")
    int updateBoardHit(int boardId);

    @Insert("insert into board(title, content, member_id) values(#{title}, #{content}, #{member_id})")
    int setBoardReg(BoardRegDto boardRegDto);

    @Update("update board set delete_state = true where id = #{boardId} and member_id = #{userId}")
    int deleteBoard(int boardId, int userId);

    @Update("update board set title = #{title}, content = #{content}, modify_date = now() where id = #{id} and member_id = #{userId}")
    int modifyBoard(@Param("title") String title, @Param("content") String content, @Param("id") int boardId, @Param("userId") int userId);
}
