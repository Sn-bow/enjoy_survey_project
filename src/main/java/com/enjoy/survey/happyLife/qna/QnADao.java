package com.enjoy.survey.happyLife.qna;


import com.enjoy.survey.happyLife.qna.dto.QnAQuestionModifyDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QnADao {

    // TODO: QnA에서도 페이징이 필요함
    @Select("select * from question_and_answer")
    List<QnAEntity> getQnAList();

    @Select("select * from question_and_answer where id = #{qnaId}")
    QnAEntity getQnA(int qnaId);

    @Select("select answer from question_and_answer where id = #{qnaId}")
    String divisionAnswer(int qnaId);

    @Insert("insert into question_and_answer(question, member_id) values(#{question}, #{userId})")
    int setQnAQuestion(String question, int userId);

    @Update("update question_and_answer set question = #{question} where id = #{qnaId}")
    int modifyQnA(QnAQuestionModifyDto qnAQuestionModifyDto);

    @Update("update question_and_answer set delete_state = true where id = #{qnaId}")
    int deleteQnA(int qnaId);

    //TODO : answer 의 경우 admin 다오에서 접근

}
