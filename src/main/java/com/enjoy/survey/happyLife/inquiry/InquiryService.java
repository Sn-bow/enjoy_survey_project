package com.enjoy.survey.happyLife.inquiry;


import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryQuestionRegDto;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final UserDao userDao;
    private final InquiryDao inquiryDao;

    public List<InquiryEntity> getInquiryList(String jwtToken) {

        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.findByUsername(username).getId();

        // TODO: 1대1 설문에서도 페이징이 필요함
//        String rSearch = "%" + search + "%";
//        int rPage = (page - 1) * 10;
//        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "1대1문의");
//        String filter = orderSwitch.get("filter");
//        String orderBy = orderSwitch.get("orderBy");

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
