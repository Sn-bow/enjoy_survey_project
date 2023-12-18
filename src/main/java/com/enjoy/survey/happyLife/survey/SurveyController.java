package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.survey.dto.SurveyAttendDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyDeleteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Tag(name = "설문 컨트롤러", description = "설문과 관련된 API")
@RestController
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @Operation(summary = "설문 리스트 페이징, 리스트 총 개수 출력", description = "사용자가 설문 리스트 페이지 접속시에 페이징 처리된 리스트가 출력됩니다.")
    @GetMapping("/survey/list")
    public HashMap<String, Object> getSurveyList(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "order", defaultValue = "최신 순서") String order) {

        HashMap<String, Object> data = new HashMap<>();
        data.put("count", surveyService.getSurveyCount(page,search,order));
        data.put("survey_list", surveyService.getSurveyList(page,search,order));
        return data;
    }

    @Operation(summary = "설문 디테일 페이지", description = "설문 디테일 페이지로 설문 마감 전 페이지에서 필요한 API")
    @GetMapping("/user/survey/detail")
    public HashMap<String, Object> getSurveyDetail(@RequestParam(name = "survey_id") int survey_id) {
        // TODO : Front 서버 에서 유저 정보를 요청한다음 getSurveyDetail() 에 들어있는 함수중 member_id 와 일치하는 값이 있다면 수정페이지로 전환
        // TODO : 또한 설문에 등록한 사진의 경우 또다른 API 를 요청하여 사진 url을 얻어야함
        return surveyService.getSurveyDetail(survey_id);
    }

    @Operation(summary = "설문 디테일 페이지(End)", description = "설문 디테일 페이지로 설문 마감 후 페이지에서 필요한 API")
    @GetMapping("/user/survey/afterClosing/detail")
    public HashMap<String, Object> getSurveyEndDetail(@RequestParam(name = "survey_id") int survey_id) {
        // TODO : 마감후 데이터 쿼리문 작성 후 API 작성
        return null;
    }


    // 설문 등록 API
    @Operation(summary = "설문 등록 API", description = "사용자가 Form 입력시 설문을 등록합니다.")
    @PostMapping(value = "/user/survey/reg", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String setSurvey(@RequestPart("topic_id") int topic_id,
                            @RequestPart("survey_content") String survey_content,
                            @RequestPart("end_date") String end_date,
                            @RequestPart("questions") List<String> questions,
                            @RequestPart("file") MultipartFile file,
                            @RequestHeader(value = "Authorization") String jwtToken
    ) throws Exception {

        // Authentication 에서 user정보를 받는대신 JWT 토큰에서 유저정보 추출로 변경
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);

        Timestamp endDate = Timestamp.valueOf(end_date + " 00:00:00");
        for(String q : questions) {
            System.out.println(q);
        }
        try {
            surveyService.setSurvey(topic_id, survey_content, endDate, file, questions ,username);
        }catch (IOException e) {
            e.printStackTrace();
            // TODO : 에러 발생시 message 프론트 서버로 전송 수정하기
            throw new Exception("어떤 문제가 발생했습니다.");
        }

        return "complete_save_survey";
    }

    @Operation(summary = "설문 삭제 API", description = "로그인한 사용자가 설문 작성 user_id 와 일치할경우 삭제 가능")
    @PostMapping("/user/survey/delete")
    public String deleteSurvey(@RequestBody SurveyDeleteDto surveyId, @RequestHeader(value = "Authorization") String jwtToken) {
        // Authentication 에서 user정보를 받는대신 JWT 토큰에서 유저정보 추출로 변경
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);

        int result = surveyService.deleteSurvey(surveyId, username);

        if (result > 0) {
            return "complete delete survey";
        }

        return "false delete survey";
    }

    // 설문 참여 API
    @Operation(summary = "설문 참여 API", description = "로그인한 사용자가 설문을 참여할 수 있는 API")
    @PostMapping("/user/survey/attend")
    public String attendSurvey(@RequestBody SurveyAttendDto surveyAttendDto, @RequestHeader("Authorization") String jwtToken) throws Exception {
        // TODO : Authentication 에 강제 로그인 진행으로 저장된 유저 객체를 가지고 오는게 아닌 Header에
        //  저장된 JWT 토큰을 사용하여 파싱해서 username을 가지고 오기

        String username = new JWTUsernameCheck().usernameCheck(jwtToken);

        int result = surveyService.attendSurvey(surveyAttendDto.getQuestion_id(), username);

        if(result > 0) {
            return "complete survey attend";
        }else {
            throw new Exception("설문 참여에 실패하셨습니다.");
        }

    }

    @Operation(summary = "설문 참여 수정 API", description = "로그인한 사용자가 이미 참여한 설문을 수정 수 있는 API")
    @PostMapping("/user/survey/attend/update")
    public String attendSurveyUpdate(@RequestBody SurveyAttendDto surveyAttendDto, @RequestHeader("Authorization") String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);

        int result = surveyService.attendSurveyUpdate(surveyAttendDto.getQuestion_id(), username);

        return result > 0 ? "survey question option update complete" : "false survey question option update";
    }


    // TODO : Swagger 적용 완료 , Swagger 세부사항 적어야함 : api 완성후 세부사항 적기

    // TODO : 어드민 계정에서 사용자들이 등록한 설문을 삭제할 수 있게 해주어야함 후에 어드민 컨트롤러에 만들어야함
//    @PostMapping("/admin/survey/delete")
//    public String deleteSurvey(@RequestBody String temp) {
//
//        return "complete delete survey";
//    }

}
