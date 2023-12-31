package com.enjoy.survey.happyLife.file;


import lombok.Builder;
import lombok.Data;

@Data
public class FileEntity {

    int id;
    String orgNm;
    String savedNm;
    String savedPath;
    int boardId;

    @Builder
    public FileEntity(int id, String orgNm, String savedNm, String savedPath, int boardId) {
        this.id = id;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
        this.boardId = boardId;
    }
}
