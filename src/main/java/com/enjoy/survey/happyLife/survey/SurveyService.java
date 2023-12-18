package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.user.UserDao;
import com.enjoy.survey.happyLife.user.UserEntity;
import com.enjoy.survey.happyLife.survey.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyDao surveyDao;
    private final UserDao userDao;
    private final SurveyPictureService surveyPictureService;


    // getSurveyList 와 getSurveyCount 의 쿼리를 합쳐서 사용할 수 있도록 해보면 좋을것 같음
    public List<SurveyEntity> getSurveyList(int page, String search, String order) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        OrderSwitch orderSwitch = new OrderSwitch();
        return surveyDao.getSurveyList(rPage, rSearch, orderSwitch.switching(order).get(0), orderSwitch.switching(order).get(1));
    }

    public int getSurveyCount(int page, String search, String order) {
        String rSearch = "%" + search + "%";
        return surveyDao.getSurveyCount(rSearch);
    }

    public HashMap<String, Object> getSurveyDetail(int surveyId) {
        SurveyDetailDto surveyDetailDto = surveyDao.getSurveyDetail1(surveyId);
        List<QuestionDto> questionDtoList = surveyDao.getSurveyDetail2(surveyId);
        List<AlreadyAttendSurveyUserDto> alreadyAttendSurveyUserDtoList = surveyDao.getSurveyDetail3(surveyId);

        HashMap<String, Object> detailData = new HashMap<>();
        detailData.put("detailData", surveyDetailDto);
        detailData.put("detailQuestion", questionDtoList);
        detailData.put("alreadyUser", alreadyAttendSurveyUserDtoList);

        return detailData;
    }


    public int setSurvey(int topic_id, String survey_content,
                         Timestamp end_date, MultipartFile file,
                         List<String> questions, String username
    ) throws IOException {
        SurveyRegDto surveyRegDto = new SurveyRegDto();
        surveyRegDto.setTopic_id(topic_id);
        surveyRegDto.setSurvey_content(survey_content);
        surveyRegDto.setEnd_date(end_date);

        // userId 를 찾아 설문 등록 시에 어떤 유저가 등록 하였는지 알기 위해 userId를 select하여 값을 넘겨줌
        int userId = userDao.findByUsername(username).getId();
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

    // 등록된 설문 삭제 서비스단 : 현재 쿼리가 delete로 되어있음 리팩토링때 update로 delete_state를  true 로 변경하는 방식으로 바꿔보기
    public int deleteSurvey(SurveyDeleteDto surveyId, String username) {
        UserEntity user = userDao.findByUsername(username);
        int result = surveyDao.deleteSurvey(surveyId.getSurveyId(), user.getId());
        if (result > 0) {
            surveyDao.deleteSurveyPicture(surveyId.getSurveyId());
            surveyDao.deleteSurveyQuestion(surveyId.getSurveyId());
        }
        return result;
    }

    public int attendSurvey(int sqId, String username){
        int userId = userDao.findByUsername(username).getId();
        // TODO : survey question id가 내가 선택한 survey id 와 일치 하는지 조건문 작성 필요
        // TODO : 이미 같은 유저가 설문에 참여하였으면 참여하지 못하도록 설정 : 그럼 Detail 페이지에서 참여 유저정보를 보내줘야함
        // TODO : 설문 참여시에 설문의 참여인원 증가 시키기
//        surveyDao.updateSurveyHit(1,2);
        return surveyDao.attendSurvey(sqId, userId);
    }

    public int attendSurveyUpdate(int sqId, String username) {
        int userId = userDao.findByUsername(username).getId();
        // TODO : survey question id가 내가 선택한 survey id 와 일치 하는지 조건문 작성 필요
        return surveyDao.attendSurveyUpdate(sqId, userId);
    }

}
