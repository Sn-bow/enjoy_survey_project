package com.enjoy.survey.happyLife.qna;

import com.enjoy.survey.happyLife.qna.dto.QnAQuestionModifyDto;
import com.enjoy.survey.happyLife.qna.dto.QnAQuestionRegDto;
import com.enjoy.survey.happyLife.user.JWTUsernameCheck;
import com.enjoy.survey.happyLife.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QnAService {

    private final QnADao qnADao;
    private final UserDao userDao;

    public List<QnAEntity> getQnAList() {
        return qnADao.getQnAList();
    }

    public QnAEntity getQnA(int qnaId) {
        return qnADao.getQnA(qnaId);
    }

    public int setQnAQuestion(QnAQuestionRegDto qnAQuestionRegDto, String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.findByUsername(username).getId();
        return qnADao.setQnAQuestion(qnAQuestionRegDto.getQuestion(), userId);
    }

    // TODO : admin 부분에서 생성하기
    public int setQnAAnswer() {
        return 0;
    }

    public int modifyQnAQuestion(QnAQuestionModifyDto qnAQuestionModifyDto) {
        String answer = qnADao.divisionAnswer(qnAQuestionModifyDto.getQnaId());
        if (answer != null) {
            return qnADao.modifyQnA(qnAQuestionModifyDto);
        }else {
            return 0;
        }
    }

    public int deleteQnA(int qnaId) {
        String answer = qnADao.divisionAnswer(qnaId);

        if (answer != null) {
            return qnADao.deleteQnA(qnaId);
        }else {
            return 0;
        }
    }
}
