package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
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

    @GetMapping("/user/surveyList")
    public List<SurveyEntity> getSurveyList(int page, String search, String filter) {

        return null;
    }


    // 설문 등록 API
    @PostMapping(value = "/user/surveyReg", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String setSurvey(@RequestPart("topic_id") int topic_id,
                            @RequestPart("survey_content") String survey_content,
                            @RequestPart("end_date") String end_date,
                            @RequestPart("questions") List<String> questions,
                            @RequestPart("file") MultipartFile file,
                            Authentication authentication
    ) {
        Timestamp endDate = Timestamp.valueOf(end_date + " 00:00:00");
        //TODO : question 작업 진행 해야함
        for(String q : questions) {
            System.out.println(q);
        }
        try {
            surveyService.setSurvey(topic_id, survey_content, endDate, file ,authentication);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return "complete_save_survey";
    }

}
