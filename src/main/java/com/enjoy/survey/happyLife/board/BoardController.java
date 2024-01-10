package com.enjoy.survey.happyLife.board;


import com.enjoy.survey.happyLife.board.dto.BoardDeleteDto;
import com.enjoy.survey.happyLife.board.dto.BoardFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardModifyFormDto;
import com.enjoy.survey.happyLife.board.dto.BoardRegDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> setBoardReg(
            @RequestPart(name = "title", required = true) String title,
            @RequestPart(name = "content", required = true) String content,
            @RequestPart(name = "file", required = false) List<MultipartFile> files,
            @RequestHeader(value = "Authorization") String jwtToken
    ) throws Exception {
        BoardRegDto boardRegDto = new BoardRegDto();
        boardRegDto.setTitle(title);
        boardRegDto.setContent(content);
        int result = boardService.setBoardReg(boardRegDto, files, jwtToken);
        if(result > 0) {
            return ResponseEntity.ok("성공적으로 게시물을 등록하였습니다.");
        }else {
            return ResponseEntity.status(400).body("게시글 등록에 실패하였습니다.");
        }
    }

    // TODO : 게시글 수정시에 등록되어있는 파일을 삭제할 수있는 API를 만들어야함
    // TODO : 게시글 수정시에 다시 파일을 등록할 수있는 코드를 추가하여야함
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
    public String deleteBoard(
            @RequestBody Integer boardId,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception {
        int result = boardService.deleteBoard(boardId, jwtToken);
        if (result > 0) {
            return "성공적으로 삭제에 성공하셨습니다.";
        }else {
            throw new Exception("삭제에 실패하셨습니다.");
        }
    }

    @Operation(summary = "board 선택 삭제 API", description = "board 선택 삭제 API")
    @PostMapping("/user/board/deleteList")
    public String choiceDeleteBoard(
            @RequestBody BoardDeleteDto boardDeleteDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) throws Exception {
        int result = boardService.choiceDeleteBoard(boardDeleteDto.getBoardIds(), jwtToken);
        if(result > 0) {
            return "성공적으로 게시글들을 삭제 하였습니다.";
        }else {
            throw new Exception("게시글들을 삭제하는데 문제가 발생했습니다.");
        }
    }

}
