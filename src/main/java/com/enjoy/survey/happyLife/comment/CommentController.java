package com.enjoy.survey.happyLife.comment;


import com.enjoy.survey.happyLife.comment.dto.CommentDeleteDto;
import com.enjoy.survey.happyLife.comment.dto.CommentListDto;
import com.enjoy.survey.happyLife.comment.dto.CommentModifyDto;
import com.enjoy.survey.happyLife.comment.dto.CommentRegDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;

    @Operation(summary = "댓글 리스트 출력 API",description = "댓글 리스트 출력 API")
    @PostMapping("/user/comment/list")
    public List<CommentEntity> getCommentList(@RequestBody CommentListDto commentListDto) {
        return commentService.getCommentList(commentListDto.getBoardId());
    }


    @Operation(summary = "댓글 등록 API",description = "댓글 등록 API")
    @PostMapping("/user/comment/reg")
    public String setComment(@RequestBody CommentRegDto commentRegDto) throws Exception {
        int result = commentService.setComment(commentRegDto);
        if(result > 0) {
            return "댓글 등록에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 등록에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 수정 API",description = "댓글 수정 API")
    @PostMapping("/user/comment/modify")
    public String modifyComment(@RequestBody CommentModifyDto commentModifyDto) throws Exception {
        int result = commentService.modifyComment(commentModifyDto);
        if(result > 0) {
            return "댓글 수정에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 수정에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 삭제 API",description = "댓글 삭제 API")
    @PostMapping("/user/comment/delete")
    public String deleteComment(@RequestBody CommentDeleteDto commentDeleteDto) throws Exception{
        int result = commentService.deleteComment(commentDeleteDto);
        if(result > 0) {
            return "댓글 삭제에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 삭제에 실패하였습니다");
        }
    }


}
