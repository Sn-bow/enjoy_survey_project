package com.enjoy.survey.happyLife.user;


import com.enjoy.survey.happyLife.board.BoardEntity;
import com.enjoy.survey.happyLife.comment.CommentEntity;
import com.enjoy.survey.happyLife.common.OrderSwitch;
import com.enjoy.survey.happyLife.qna.QnAEntity;
import com.enjoy.survey.happyLife.user.dto.UserAttendSurveyDto;
import com.enjoy.survey.happyLife.user.dto.UserInfoDto;
import com.enjoy.survey.happyLife.user.dto.UserSignUpDto;
import com.enjoy.survey.happyLife.user.dto.UserSimpleInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity user() {
        return userDao.findByUsername("tester");
    }


    public int userSignUp(UserSignUpDto user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userDao.signUp(user);
    }

    public int userInfoModify(UserSignUpDto user, String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        UserEntity userBeforeInfo = userDao.findByUsername(username);

        String email = user.getEmail().isEmpty() ? userBeforeInfo.getEmail() : user.getEmail();
        String name = user.getName().isEmpty() ? userBeforeInfo.getName() : user.getName();
        String birth = user.getBirth().isEmpty() ? userBeforeInfo.getBirth() : user.getBirth();
        String gender = user.getGender().isEmpty() ? userBeforeInfo.getGender() : user.getGender();

        int result = userDao.userInfoModify(email, name, birth, gender, username);
        return result;
    }

    public void selectTopic(String username, List<Integer> listSelectTopicId) {
        int userId = userDao.findByUsername(username).getId();
        for (int topic : listSelectTopicId) {
            userDao.saveSelectedTopic(userId, topic);
        }
    }

    public UserInfoDto getUserInfo(String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        return userDao.getUserInfo(username);
    }

    public UserSimpleInfoDto getUserSimpleInfo(String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        return userDao.getUserSimpleInfo(username);
    }

    // TODO : order 수정 필요
    public HashMap<String, Object> getBoardListForUser(int page, String search, String order, String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.getUserSimpleInfo(username).getId();

        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "게시판");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        List<BoardEntity> boardList = userDao.getBoardListForUser(
                rPage, rSearch, filter, orderBy, userId
        );
        int count = userDao.getBoardCountForUser(rSearch, userId);

        HashMap<String, Object> boardListAndCount = new HashMap<>();
        boardListAndCount.put("boardList", boardList);
        boardListAndCount.put("count", count);
        return boardListAndCount;

    }

    public List<CommentEntity> getCommentListForUser(String jwtToken) {
        // TODO : comment List 출력시에 페이징 처리를 해야할것 같음
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.getUserSimpleInfo(username).getId();
        return userDao.getCommentListForUser(userId);
    }

    public List<QnAEntity> getQnAListForUser(String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.getUserSimpleInfo(username).getId();
        return userDao.getQnAListForUser(userId);
    }

    public HashMap<String, Object> getSurveyListForUser(int page, String search, String order, String jwtToken) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.getUserSimpleInfo(username).getId();

        HashMap<String, String> orderSwitch = new OrderSwitch().switching(order, "설문");
        String filter = orderSwitch.get("filter");
        String orderBy = orderSwitch.get("orderBy");

        HashMap<String, Object> data = new HashMap<>();
        data.put("surveyList", userDao.getSurveyListForUser(
                rPage, rSearch, filter, orderBy, userId
        ));
        data.put("count", userDao.getSurveyListCountForUser(rSearch, userId));

        return data;
    }

    public HashMap<String, Object> getSurveyAttendListForUser(String jwtToken, String search, int page) {
        int rPage = (page - 1) * 10;
        String rSearch = "%" + search + "%";
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        int userId = userDao.getUserSimpleInfo(username).getId();

        // TODO : Paging 하고 order by 해야함
        List<UserAttendSurveyDto> userAttendSurveyDtos = userDao.getSurveyAttendListForUser(userId, rSearch, rPage);
        int surveyAttendCount = userDao.getSurveyAttendCount(userId, rSearch);

        HashMap<String, Object> data = new HashMap<>();
        data.put("surveyAttendListForUser", userAttendSurveyDtos);
        data.put("surveyAttendCount", surveyAttendCount);
        return data;
    }

    public int leaveUser(String jwtToken) {
        String username = new JWTUsernameCheck().usernameCheck(jwtToken);
        return userDao.leaveUser(username);
    }

}
