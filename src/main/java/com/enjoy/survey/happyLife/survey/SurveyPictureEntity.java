package com.enjoy.survey.happyLife.survey;


import lombok.Builder;
import lombok.Data;

@Data
public class SurveyPictureEntity {

    Long id;
    String orgNm;
    String savedNm;
    String savedPath;
    int survey_id;

    @Builder
    public SurveyPictureEntity(Long id, String orgNm, String savedNm, String savedPath, int survey_id) {
        this.id = id;
        this.orgNm = orgNm;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
        this.survey_id = survey_id;
    }

}
