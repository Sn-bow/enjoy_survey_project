package com.enjoy.survey.happyLife.board;


import com.enjoy.survey.happyLife.board.dto.BoardDeleteDto;
import com.enjoy.survey.happyLife.board.dto.BoardFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardModifyFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardRegDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "board list 요청 API", description = "board list 요청 API")
    @GetMapping("/board/list")
    public HashMap<String, Object> getBoardList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order
            ) {
        return boardService.getBoardList(page, search, order);
    }

    @Operation(summary = "board detail 요청 API", description = "board detail 요청 API")
    @GetMapping("/user/board/detail")
    public HashMap<String, Object> getBoardDetail(@RequestParam(name = "boardId") int boardId) throws Exception {
        return boardService.getBoardDetail(boardId);
    }

    @Operation(summary = "board 등록 API", description = "board 등록 API")
    @PostMapping("/user/board/reg")
    public String setBoardReg(
            @RequestBody BoardFormDto boardFormDto,
            @RequestHeader(name = "Authorization") String jwtToken) throws Exception {
        // TODO : 후에 RequestPart 로 변경하여 FormData 로 변경하여 받아야함
        //  or 따로 API 를 만들어서 file FormData를 받아서 DB 에 저장
        BoardRegDto boardRegDto = new BoardRegDto();
        boardRegDto.setTitle(boardFormDto.getTitle());
        boardRegDto.setContent(boardFormDto.getContent());
        int result = boardService.setBoardReg(boardRegDto, jwtToken);
        if(result > 0) {
            return "성공적으로 게시물을 등록하였습니다.";
        }else {
            throw new Exception("게시물을 등록하지 못하였습니다.");
        }
    }

    @Operation(summary = "board 수정 API", description = "board 수정 API")
    @PostMapping("/user/board/modify")
    public String modifyBoard(
            @RequestBody BoardModifyFormDto boardModifyFormDto,
            @RequestHeader(name = "Authorization") String jwtToken) throws Exception {
        int result = boardService.modifyBoard(boardModifyFormDto, jwtToken);
        if(result > 0) {
            return "성공적으로 게시물을 수정하였습니다.";
        }else {
            throw new Exception("게시물을 수정하지 못하였습니다.");
        }
    }

    @Operation(summary = "board 삭제 API", description = "board 삭제 API")
    @PostMapping("/user/board/delete")
    public String deleteBoard(@RequestParam(name = "boardId") int boardId) throws Exception {
        int result = boardService.deleteBoard(boardId);
        if (result > 0) {
            return "성공적으로 삭제에 성공하셨습니다.";
        }else {
            throw new Exception("삭제에 실패하셨습니다.");
        }
    }

    @Operation(summary = "board 선택 삭제 API", description = "board 선택 삭제 API")
    @PostMapping("/user/board/deleteList")
    public String choiceDeleteBoard(@RequestBody BoardDeleteDto boardDeleteDto) throws Exception {
        int result = boardService.choiceDeleteBoard(boardDeleteDto.getBoardIds());
        if(result > 0) {
            return "성공적으로 게시글들을 삭제 하였습니다.";
        }else {
            throw new Exception("게시글들을 삭제하는데 문제가 발생했습니다.");
        }
    }

}
