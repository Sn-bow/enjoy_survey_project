package com.enjoy.survey.happyLife.qna;


import com.enjoy.survey.happyLife.qna.dto.QnAQuestionModifyDto;
import com.enjoy.survey.happyLife.qna.dto.QnAQuestionRegDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QnAController {

    private final QnAService qnAService;

    @Operation(summary = "QnA list 출력 API", description = "QnA list 출력 API")
    @GetMapping("/qna/list")
    public List<QnAEntity> getQnAList() {
        // TODO: QnA에서도 페이징이 필요함
        return qnAService.getQnAList();
    }

    @Operation(summary = "QnA detail 출력 API", description = "QnA detail 출력 API")
    @GetMapping("/qna/detail")
    public QnAEntity getQnA(@RequestParam(name = "qnaId") int qnaId) {
        return qnAService.getQnA(qnaId);
    }

    @Operation(summary = "QnA question 등록 API", description = "QnA question 등록 API")
    @PostMapping("/user/qna/question")
    public String setQnAQuestion(
            @RequestBody QnAQuestionRegDto qnAQuestionRegDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        int result = qnAService.setQnAQuestion(qnAQuestionRegDto, jwtToken);
        if (result > 0) {
            return "QnA등록을 성공적으로 마치셨습니다.";
        }else {
            return "QnA등록에 실패하셨습니다.";
        }
    }

    @Operation(summary = "QnA 삭제 API", description = "QnA 삭제 API")
    @PostMapping("/user/qna/question/delete")
    public String setQnADelete(
            @RequestBody Integer qnaId,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        int result = qnAService.deleteQnA(qnaId, jwtToken);
        if (result > 0) {
            return "QnA의 삭제가 완료 되었습니다.";
        }else {
            return "QnA의 답변이 존재하는경우 삭제하실 수 없습니다.";
        }
    }

    // TODO : question 부분 answer가 존재하지 않을때 수정할 수 있어야함
    @Operation(summary = "QnA 수정 API", description = "QnA 중 Answer 이 없을경우 수정을 할 수 있는 API")
    @PostMapping("/user/qna/question/modify")
    public String modifyQnAQuestion(
            @RequestBody QnAQuestionModifyDto qnAQuestionModifyDto,
            @RequestHeader(name = "Authorization") String jwtToken
    ) {
        int result = qnAService.modifyQnAQuestion(qnAQuestionModifyDto, jwtToken);
        if (result > 0) {
            return "QnA 의 질문 부분의 수정이 완료되었습니다.";
        }else {
            return "이미 답변이 존재하면 질문을 수정할 수 없습니다";
        }
    }
}
