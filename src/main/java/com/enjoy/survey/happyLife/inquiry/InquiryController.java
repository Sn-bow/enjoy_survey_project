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
        } else {
            throw new Exception("1대1 문의 등록에 실패하였습니다.");
        }
    }



    // TODO : 1 대 1 문의 삭제 API 작성 필요 : delete_state 활용
    @Operation(summary = "1 대 1 문의 삭제 API", description = "1 대 1 문의 삭제 API answer가 존재시에는 삭제 불가능 (admin 만 삭제 가능)")
    @PostMapping("/user/inquiry/delete")
    public String deleteInquiry(@RequestParam(name = "inquiryId") int inquiryId) {
        int result = inquiryService.deleteInquiry(inquiryId);
        if(result > 0) {
            return "1대1 문의 삭제가 정상적으로 완료되었습니다.";
        }else {
            return "1대1 문의 삭제에 실패하셨습니다.";
        }
    }
    // TODO : 1 대 1 문의 answer가 존재하지 않을때 수정할 수 있어야함
    @Operation(summary = "1 대 1 문의 question 수정 API", description = "1 대 1 문의 수정 API answer가 존재시에는 수정 불가능")
    @PostMapping("/user/inquiry/question/modify")
    public String modifyInquiryQuestion(
            @RequestBody String question, // TODO : dto 만들어서 수정
            @RequestBody Integer inquiryId
    ) {
        int result = inquiryService.modifyInquiry(question, inquiryId);
        if(result > 0) {
            return "1대1 문의 수정이 정상적으로 완료되었습니다.";
        }else {
            return "1대1 문의 수정이 실패하셨습니다.";
        }
    }
}
