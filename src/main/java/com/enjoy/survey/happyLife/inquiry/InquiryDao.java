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

    @Select("select * from one_to_one_inquiry where member_id = #{userId}")
    List<InquiryEntity> getInquiryList(int userId);

    @Select("select * from one_to_one_inquiry where id = #{inquiry_id}")
    InquiryEntity getInquiry(int inquiry_id);

    @Insert("insert into one_to_one_inquiry(question, member_id) values(#{question}, #{user_id})")
    int setInquiryQuestion(InquiryQuestionRegDto inquiryQuestionRegDto);

    @Update("update one_to_one_inquiry set answer = #{answer} where id = #{inquiry_id}")
    int setInquiryAnswer(InquiryAnswerRegDto inquiryAnswerRegDto);
}
