package com.enjoy.survey.happyLife.file;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {
    @Insert("insert into file(orgNm, savedNm, savedPath, board_id) values(#{orgNm}, #{savedNm}, #{savedPath}, #{boardId})")
    int save(FileEntity file);
}
