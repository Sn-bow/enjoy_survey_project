package com.enjoy.survey.happyLife.inquiry;


import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryQuestionRegDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @Operation(summary = "1대1 question, answer List 출력", description = "1대1 question, answer List 출력 API")
    @GetMapping("/user/inquiry/list")
    public List<InquiryEntity> getInquiryList(@RequestHeader(name = "Authorization") String jwtToken) {
        // TODO : 리스트 출력시 1대1 문의가 많을경우 페이징 처리를 해야주어야 한다.
        return inquiryService.getInquiryList(jwtToken);
    }

    @Operation(summary = "1대1 question, answer 출력", description = "1대1 question, answer 출력 API")
    @GetMapping("/user/inquiry")
    public InquiryEntity getInquiry(@RequestParam(name = "inquiryId") int inquiryId) {
        return inquiryService.getInquiry(inquiryId);
    }

    @Operation(summary = "1대1 질문 등록", description = "일반 유저의 1대1 질문 등록 API")
    @PostMapping("/user/inquiry/question")
    public String setInquiryQuestion(@RequestBody InquiryQuestionRegDto inquiryQuestionRegDto) throws Exception {
        int result = inquiryService.setInquiryQuestion(inquiryQuestionRegDto);
        if (result > 0) {
            return "1대1 문의를 등록하셨습니다";
        }else {
          throw new Exception("1대1 문의 등록에 실패하였습니다.");
        }
    }

    // admin 폴더에 생성할 맵핑
    @Operation(summary = "1대1 답변 등록", description = "관리자의 1대1 답변 등록 API")
    @PostMapping("/admin/inquiry/answer")
    public String setInquiryAnswer(@RequestBody InquiryAnswerRegDto inquiryAnswerRegDto) throws Exception {
        int result = inquiryService.setInquiryAnswer(inquiryAnswerRegDto);
        if (result > 0) {
            return "1대1 문의 답변을 등록하셨습니다";
        }else {
            throw new Exception("1대1 문의 답변 등록에 실패하였습니다.");
        }
    }


}
