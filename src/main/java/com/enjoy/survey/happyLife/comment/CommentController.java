package com.enjoy.survey.happyLife.comment;


import com.enjoy.survey.happyLife.comment.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {


    private final CommentService commentService;


    @Operation(summary = "댓글 등록 API",description = "댓글 등록 API")
    @PostMapping("/user/comment/reg")
    public String setComment(
            @RequestBody CommentRegDto commentRegDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception {
        int result = commentService.setComment(commentRegDto, jwtToken);
        if(result > 0) {
            return "댓글 등록에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 등록에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 수정 API",description = "댓글 수정 API")
    @PostMapping("/user/comment/modify")
    public String modifyComment(
            @RequestBody CommentModifyDto commentModifyDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception {
        // 댓글 수정시 본인이 맞는지에 대한 부분 토큰으로 검증
        int result = commentService.modifyComment(commentModifyDto, jwtToken);
        if(result > 0) {
            return "댓글 수정에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 수정에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 삭제 API",description = "작성한 댓글 삭제 API")
    @PostMapping("/user/comment/delete")
    public String deleteComment(
            @RequestBody Integer cmtId,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception{
        int result = commentService.deleteComment(cmtId, jwtToken);
        if(result > 0) {
            return "댓글 삭제에 성공하셨습니다.";
        }else {
            throw new Exception("댓글 삭제에 실패하였습니다");
        }
    }

    @Operation(summary = "댓글 리스트 삭제 API",description = "작성한 댓글 리스트 삭제 API")
    @PostMapping("/user/commentList/delete")
    public String choiceDeleteComment(
            @RequestBody List<Integer> cmtIds,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception {
        int result = commentService.choiceDeleteComment(cmtIds, jwtToken);
        if (result > 0) {
            return "선택한 댓글이 삭제를 완료 하였습니다.";
        }else {
            throw new Exception("선택한 댓글 삭제에 실패 하였습니다");
        }
    }


}
