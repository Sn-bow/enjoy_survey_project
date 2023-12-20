package com.enjoy.survey.happyLife.inquiry;


import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryQuestionRegDto;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final UserDao userDao;
    private final InquiryDao inquiryDao;

    public List<InquiryEntity> getInquiryList(String jwtToken) {
        // TODO: 1대1 설문에서도 페이징이 필요함
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.findByUsername(username).getId();

        return inquiryDao.getInquiryList(userId);
    }

    public InquiryEntity getInquiry(int inquiryId) {
        return inquiryDao.getInquiry(inquiryId);
    }

    public int setInquiryQuestion(InquiryQuestionRegDto inquiryQuestionRegDto) {
        return inquiryDao.setInquiryQuestion(inquiryQuestionRegDto);
    }

    public int setInquiryAnswer(InquiryAnswerRegDto inquiryAnswerRegDto) {
        return inquiryDao.setInquiryAnswer(inquiryAnswerRegDto);
    }


}
