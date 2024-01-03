package com.enjoy.survey.happyLife.comment;


import com.enjoy.survey.happyLife.comment.dto.CommentModifyDto;
import com.enjoy.survey.happyLife.comment.dto.CommentRegDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentDao {


    // comment 리스트 출력
    // TODO : comment List 출력시에 페이징 처리를 해야할것 같음
    @Select("select * from comment where board_id = #{boardId} and delete_state = false")
    List<CommentEntity> getCommentList(int boardId);

    // comment 등록
    @Insert("insert into comment(content, board_id, member_id) values(#{content}, #{board_id}, #{member_id})")
    int setComment(CommentRegDto commentRegDto);

    // comment 수정
    @Update("update comment set content = #{content} where id = #{cmt_id}")
    int modifyComment(CommentModifyDto commentModifyDto);

    // comment 삭제
    @Update("update comment set delete_state = true where id = #{cmt_id} and member_id = #{userId}")
    int deleteComment(int cmt_id, int userId);

    // board에 연관된 댓글 삭제
    @Update("update comment set delete_state = true where board_id = #{boardId}")
    int deleteCommentToBoard(int boardId);

}
