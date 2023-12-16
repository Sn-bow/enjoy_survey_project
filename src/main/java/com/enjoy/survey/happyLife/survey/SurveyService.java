package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.User.UserDao;
import com.enjoy.survey.happyLife.User.UserEntity;
import com.enjoy.survey.happyLife.survey.dto.SurveyDeleteDto;
import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyDao surveyDao;
    private final UserDao userDao;
    private final SurveyPictureService surveyPictureService;


    public List<SurveyEntity> getSurveyList(int page, String search, String order) {

        // TODO : 페이지 마다 시작 index 와 각 페이지 마다 다른 번호 순서를 출력 해야함
        String filter = "id";
        String orderBy = "desc";
        // 페이지마다 시작 값을 바꿔야함
        int rpage = page;

        switch (order) {
            case "많이 참여한 순서" -> {
                filter = "hit";
                orderBy = "desc";
                break;
            }
            case "적게 참여한 순서" -> {
                filter = "hit";
                orderBy = "asc";
                break;
            }
            case "최신 순서" -> {
                filter = "id";
                orderBy = "desc";
                break;
            }
            case "오래된 순서" -> {
                filter = "id";
                orderBy = "asc";
                break;
            }
        }

        String rSearch = "%" + search + "%";

        return surveyDao.getSurveyList(rpage, rSearch, filter, orderBy);
    }

    public SurveyEntity getSurvey(int surveyId) {
        return surveyDao.getSurvey(surveyId);
    }


    public int setSurvey(int topic_id, String survey_content,
                         Timestamp end_date, MultipartFile file,
                         List<String> questions, Authentication authentication
    ) throws IOException {
        SurveyRegDto surveyRegDto = new SurveyRegDto();
        surveyRegDto.setTopic_id(topic_id);
        surveyRegDto.setSurvey_content(survey_content);
        surveyRegDto.setEnd_date(end_date);

        // userId 를 찾아 설문 등록 시에 어떤 유저가 등록 하였는지 알기 위해 userId를 select하여 값을 넘겨줌
        int userId = userDao.findByUsername(authentication.getName()).getId();
        surveyRegDto.setMember_id(userId);

        // 설문이 정상적으로 등록되었으면
        int result = surveyDao.setSurvey(surveyRegDto);

        // 결과값에 따라서
        if (result > 0) {
            // question 과 picture에 surveyId를 넘겨 주어 등록시킴
            int surveyId = surveyDao.getSurveyId(userId);
            for(String question : questions) {
                surveyDao.setQuestion(question, surveyId);
            }
            surveyPictureService.savePicture(file, surveyId);
        }
        return result;
    }

    // 등록된 설문 삭제 서비스단
    public int deleteSurvey(SurveyDeleteDto surveyId, Authentication authentication) {
        UserEntity user = userDao.findByUsername(authentication.getName());
        int result = surveyDao.deleteSurvey(surveyId.getSurveyId(), user.getId());
        if (result > 0) {
            surveyDao.deleteSurveyPicture(surveyId.getSurveyId());
            surveyDao.deleteSurveyQuestion(surveyId.getSurveyId());
        }
        return result;
    }

}
