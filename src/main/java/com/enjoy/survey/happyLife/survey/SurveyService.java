package com.enjoy.survey.happyLife.survey;


import com.enjoy.survey.happyLife.User.UserDao;
import com.enjoy.survey.happyLife.User.UserEntity;
import com.enjoy.survey.happyLife.survey.dto.SurveyRegDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyDao surveyDao;
    private final UserDao userDao;
    private final SurveyPictureService surveyPictureService;

    public int setSurvey(int topic_id, String survey_content,
                         Timestamp end_date, MultipartFile file,
                         Authentication authentication
    ) throws IOException {
        SurveyRegDto surveyRegDto = new SurveyRegDto();

        surveyRegDto.setTopic_id(topic_id);
        surveyRegDto.setSurvey_content(survey_content);
        surveyRegDto.setEnd_date(end_date);
        // surveyRegDto.setMember_id(); 를 위해서 select * from where username = 으로 찾아야함
        int userId = userDao.findByUsername(authentication.getName()).getId();
        surveyRegDto.setMember_id(userId);

        int result = surveyDao.setSurvey(surveyRegDto);

        // survey ID 를 찾기 위해 select id from survey where member_id = #{} and where id = (select MAX(id) from survey) 을 조건으로 찾아 와야함
        // TODO : 임시로 max로 해놨지만 설문을 삭제했을경우 이와 같은 방법은 위험할 수 있으므로 코드 수정이 필요함
        if (result > 0) {
            int surveyId = surveyDao.getSurveyId(userId);
            // TODO : question 생성부분을 JSON 에서 array로 받고 해당 값을 for문으로 insert 시켜야함
//            surveyDao.setQuestion("d", surveyId);
            surveyPictureService.savePicture(file, surveyId);
        }
        return result;
    }
}
