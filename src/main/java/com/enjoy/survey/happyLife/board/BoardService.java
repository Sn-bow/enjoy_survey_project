package com.enjoy.survey.happyLife.board;


import com.enjoy.survey.happyLife.board.dto.BoardModifyFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardRegDto;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.comment.CommentService;
import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao boardDao;
    private final UserDao userDao;
    private final CommentService commentService;

    public HashMap<String, Object> getBoardList(int page, String search, String order) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";

        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "게시판");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");


        List<BoardEntity> boardList = boardDao.getBoardList(
                rPage, rSearch, filter, orderBy
        );
        int count = boardDao.getBoardCount(rSearch);

        HashMap<String, Object> boardListAndCount = new HashMap<>();
        boardListAndCount.put("boardList", boardList);
        boardListAndCount.put("count", count);
        return boardListAndCount;
    }

    public HashMap<String, Object> getBoardDetail(int boardId) throws Exception {
        // TODO : HashMap<String, Object> 타입으로 boardDetail 과 commentList 를 출력해야함
        //  : 그러므로 이후 comment List 출력부분 생성후 코드 수정 필요
        BoardEntity board = boardDao.getBoardDetail(boardId);
        List<CommentEntity> commentList = commentService.getCommentList(boardId);
        HashMap<String, Object> boardDetailAndComments = new HashMap<>();
        boardDetailAndComments.put("boardDetail", board);
        boardDetailAndComments.put("commentList", commentList);
        if (board != null) {
            boardDao.updateBoardHit(boardId);
            return boardDetailAndComments;
        }else {
            throw new Exception("board가 존재하지 않습니다.");
        }
    }

    public int setBoardReg(BoardRegDto boardFrom, String jwtToken) {
        // TODO : 게시글 생성시에 파일 등록 부분 처리도 해주어야함
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.findByUsername(username).getId();
        boardFrom.setMember_id(userId);
        return boardDao.setBoardReg(boardFrom);
    }

    public int deleteBoard(int boardId) {
        // TODO : 게시글 삭제 작업시 댓글 삭제 작업 진행해야함
        //  : 이번엔 게시글 삭제시 delete_state가 변경되도록 되어있으므로 따로 작업하지 않음
        return boardDao.deleteBoard(boardId);
    }

    public int choiceDeleteBoard(List<Integer> boardIds) {
        int result = 0;
        for (int boardId : boardIds) {
            result = boardDao.deleteBoard(boardId);
            if(result == 0) {
                break;
            }
        }
        return result;
    }

    public int modifyBoard(BoardModifyFormDto boardModifyFormDto, String jwtToken) {
        // TODO : 수정부분 데이터가 들어오지 않았을경우 기존의 데이터를 불러와서 처리하는 작업이 필요할 수 있음 아님 프론트에서 설정
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.findByUsername(username).getId();
        String title = boardModifyFormDto.getTitle();
        String content = boardModifyFormDto.getContent();
        int boardId = boardModifyFormDto.getId();

        return boardDao.modifyBoard(title, content, boardId, userId);
    }

}
