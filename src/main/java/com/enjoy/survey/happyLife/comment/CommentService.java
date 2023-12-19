package com.enjoy.survey.happyLife.comment;


import com.enjoy.survey.happyLife.comment.dto.CommentChoiceDeleteDto;
import com.enjoy.survey.happyLife.comment.dto.CommentDeleteDto;
import com.enjoy.survey.happyLife.comment.dto.CommentModifyDto;
import com.enjoy.survey.happyLife.comment.dto.CommentRegDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;

    public List<CommentEntity> getCommentList(int boardId) {
        return commentDao.getCommentList(boardId);
    }

    public int setComment(CommentRegDto commentRegDto) {
        return commentDao.setComment(commentRegDto);
    }

    public int modifyComment(CommentModifyDto commentModifyDto) {
        return commentDao.modifyComment(commentModifyDto);
    }

    public int deleteComment(CommentDeleteDto commentDeleteDto) {
        return commentDao.deleteComment(commentDeleteDto.getCmt_id());
    }

    public int choiceDeleteComment(CommentChoiceDeleteDto commentChoiceDeleteDto) {
        int result = 0;
        for(int cmtId : commentChoiceDeleteDto.getCmt_id()) {
            result = commentDao.deleteComment(cmtId);
            if(result < 0) {
                break;
            }
        }
        return result;
    }


}
