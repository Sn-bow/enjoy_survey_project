package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.survey.dto.SurveyDeleteDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import com.enjoy.survey.happyLife.survey.dto.SurveySelectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/survey/list")
    public List<SurveyEntity> getSurveyList(@RequestParam("page") int page,@RequestParam("search") String search,@RequestParam("order") String order) {
        System.out.println(page);
        System.out.println(search);
        System.out.println(order);
        return surveyService.getSurveyList(page,search,order);
    }


    // 설문 등록 API
    @PostMapping(value = "/user/survey/reg", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String setSurvey(@RequestPart("topic_id") int topic_id,
                            @RequestPart("survey_content") String survey_content,
                            @RequestPart("end_date") String end_date,
                            @RequestPart("questions") List<String> questions,
                            @RequestPart("file") MultipartFile file,
                            Authentication authentication
    ) {
        Timestamp endDate = Timestamp.valueOf(end_date + " 00:00:00");
        //TODO : question 작업 진행 해야함
//        String[] questions = question.split(",");
        for(String q : questions) {
            System.out.println(q);
        }
        try {
            surveyService.setSurvey(topic_id, survey_content, endDate, file, questions ,authentication);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return "complete_save_survey";
    }

    @PostMapping("/user/survey/delete")
    public String deleteSurvey(@RequestBody SurveyDeleteDto surveyId, Authentication authentication) {
        int result = surveyService.deleteSurvey(surveyId, authentication);

        if (result > 0) {
            return "complete delete survey";
        }

        return "false delete survey";
    }

    // TODO : 어드민 계정에서 사용자들이 등록한 설문을 삭제할 수 있게 해주어야함 후에 어드민 컨트롤러에 만들어야함
//    @PostMapping("/admin/survey/delete")
//    public String deleteSurvey(@RequestBody String temp) {
//
//        return "complete delete survey";
//    }

}
