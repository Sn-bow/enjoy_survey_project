package com.enjoy.survey.happyLife.survey;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class SurveyPictureService {

    @Value("${file.dir}")
    private String fileDir;

    private final SurveyDao surveyDao;

    public int savePicture(MultipartFile file, int surveyId) throws IOException {
        if (file.isEmpty()) {
            return 0;
        }

        String origName = file.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        String extension = origName.substring(origName.lastIndexOf("."));

        String savedName = uuid + extension;

        String savedPath = fileDir + savedName;

        SurveyPictureEntity picture = SurveyPictureEntity.builder()
                .orgNm(origName)
                .savedNm(savedName)
                .savedPath(savedPath)
                .survey_id(surveyId)
                .build();

        file.transferTo(new File(savedPath));

        int savedPicture = surveyDao.setPicture(picture);

        return savedPicture;
    }

}
