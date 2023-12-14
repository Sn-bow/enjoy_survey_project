package com.enjoy.survey.happyLife.User;



import com.enjoy.survey.happyLife.User.dto.UserSignUpDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {

    @Select("select * from user where username = #{username}")
    UserEntity findByUsername(String username);

    @Insert("insert into user(email, username, password, role, birth, gender) values(#{email}, #{username}, #{password}, #{role}, #{birth}, #{gender})")
    int signUp(UserSignUpDto user);

    @Insert("insert into areas_of_interest(member_id, topic_id) values(#{member_id}, #{topic_id})")
    void saveSelectedTopic(int member_id, int topic_id);

}
