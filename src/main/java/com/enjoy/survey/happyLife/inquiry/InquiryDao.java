package com.enjoy.survey.happyLife.inquiry;


import com.enjoy.survey.happyLife.inquiry.dto.InquiryAnswerRegDto;
import com.enjoy.survey.happyLife.inquiry.dto.InquiryQuestionRegDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InquiryDao {


    // TODO: 1대1 설문에서도 페이징이 필요함
    @Select("select * from one_to_one_inquiry where member_id = #{userId} and delete_state = false")
    List<InquiryEntity> getInquiryList(int userId);

    @Select("select * from one_to_one_inquiry where id = #{inquiry_id}")
    InquiryEntity getInquiry(int inquiry_id);

    @Insert("insert into one_to_one_inquiry(question, member_id) values(#{question}, #{user_id})")
    int setInquiryQuestion(InquiryQuestionRegDto inquiryQuestionRegDto);

    @Update("update one_to_one_inquiry set answer = #{answer} where id = #{inquiry_id}")
    int setInquiryAnswer(InquiryAnswerRegDto inquiryAnswerRegDto);

    @Update("update one_to_one_inquiry set delete_state = true where id = #{inquiryId}")
    int deleteInquiry(int inquiryId);

    @Update("update one_to_one_inquiry set question = #{question} where id = #{inquiryId}")
    int modifyInquiryQuestion(String question, int inquiryId);
}
