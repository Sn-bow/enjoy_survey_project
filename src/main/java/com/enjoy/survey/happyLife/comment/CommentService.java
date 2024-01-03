package com.enjoy.survey.happyLife.comment;


import com.enjoy.survey.happyLife.comment.dto.CommentChoiceDeleteDto;
import com.enjoy.survey.happyLife.comment.dto.CommentDeleteDto;
import com.enjoy.survey.happyLife.comment.dto.CommentModifyDto;
import com.enjoy.survey.happyLife.comment.dto.CommentRegDto;
import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import com.enjoy.survey.happyLife.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final UserDao userDao;

    public List<CommentEntity> getCommentList(int boardId) {

        // TODO : comment List 출력시에 페이징 처리를 해야할것 같음
//        String rSearch = "%" + search + "%";
//        int rPage = (page - 1) * 10;
//        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "댓글");
//        String filter = orderSwitch.get("filter");
//        String orderBy = orderSwitch.get("orderBy");

        return commentDao.getCommentList(boardId);
    }

    public int setComment(CommentRegDto commentRegDto) {
        return commentDao.setComment(commentRegDto);
    }

    public int modifyComment(CommentModifyDto commentModifyDto) {
        return commentDao.modifyComment(commentModifyDto);
    }

    public int deleteComment(CommentDeleteDto commentDeleteDto, String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        UserEntity user = userDao.findByUsername(username);
        return commentDao.deleteComment(commentDeleteDto.getCmt_id(), user.getId());
    }

    public int choiceDeleteComment(List<Integer> cmt_ids, String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        UserEntity user = userDao.findByUsername(username);
        int result = 0;
        for(int cmtId : cmt_ids) {
            result = commentDao.deleteComment(cmtId, user.getId());
            if(result < 0) {
                break;
            }
        }
        return result;
    }


}
