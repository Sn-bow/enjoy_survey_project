package com.enjoy.survey.happyLife.survey;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.enjoy.survey.happyLife.User.JWTUsernameCheck;
import com.enjoy.survey.happyLife.config.jwt.JwtProperties;
import com.enjoy.survey.happyLife.survey.dto.SurveyAttendDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyDeleteDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyDetailDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // TODO : Survey Detail API 작성 진행 해야함
    //  & 로그인한 사용자가 해당 설문을 참여했는지 알 수 있어야하기에 survey 테이블과 참여 테이블을 join 하여 출력할 필요가 있음
    //  : 그러면 참여 테이블의 경우 유저 테이블과 조인하여야 한다 후에 설문 속성에 존재하는 End_date 시간이 되면 통계를 내야하기 때문이다.
    //  또한 List<> 형식으로 출력되는 부분이 있으므로 해당 부분을 유의하면서 작업 진행해야함
    @GetMapping("/user/survey/detail")
    public SurveyDetailDto getSruveyDetail() {
        return new SurveyDetailDto();
    }


    // 설문 등록 API
    @Operation(summary = "설문 등록 API", description = "사용자가 Form 입력시 설문을 등록합니다.")
    @PostMapping(value = "/user/survey/reg", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String setSurvey(@RequestPart("topic_id") int topic_id,
                            @RequestPart("survey_content") String survey_content,
                            @RequestPart("end_date") String end_date,
                            @RequestPart("questions") List<String> questions,
                            @RequestPart("file") MultipartFile file,
                            Authentication authentication,
                            @RequestHeader(value = "Authorization") String token
    ) throws Exception {

        // TODO : 버그 확인 현재 시큐리티로 권한처리를 위해 authentication 으로 강제 로그인을 통해 시큐리티 세셩에 사용자 정보를
        //  담은 상태로 Authentication에서 getName()으로 확인하는게 아닌 토큰 인증은 필터에서 완료하였으므로 header의
        //  JWT 토큰을 받아 파싱하여 username을 얻는 방식으로 수정하면 좋을것 같음

        Timestamp endDate = Timestamp.valueOf(end_date + " 00:00:00");
        for(String q : questions) {
            System.out.println(q);
        }
        try {
            surveyService.setSurvey(topic_id, survey_content, endDate, file, questions ,authentication);
        }catch (IOException e) {
            e.printStackTrace();
            // TODO : 에러 발생시 message 프론트 서버로 전송 수정하기
            throw new Exception("어떤 문제가 발생했습니다.");
        }

        return "complete_save_survey";
    }

    @Operation(summary = "설문 삭제 API", description = "로그인한 사용자가 설문 작성 user_id 와 일치할경우 삭제 가능")
    @PostMapping("/user/survey/delete")
    public String deleteSurvey(@RequestBody SurveyDeleteDto surveyId, Authentication authentication) {
        int result = surveyService.deleteSurvey(surveyId, authentication);

        if (result > 0) {
            return "complete delete survey";
        }

        return "false delete survey";
    }

    // 설문 참여 API
    @Operation(summary = "설문 참여 API", description = "로그인한 사용자가 설문을 참여할 수 있는 API")
    @PostMapping("/user/survey/attend")
    public String setSurvey(@RequestBody SurveyAttendDto surveyAttendDto, @RequestHeader("Authorization") String jwtToken) throws Exception {
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
