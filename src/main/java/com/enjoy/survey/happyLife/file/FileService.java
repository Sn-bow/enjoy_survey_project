package com.enjoy.survey.happyLife.file;


import com.enjoy.survey.happyLife.board.BoardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.dir}")
    private String fileDir;

    private final FileDao fileDao;

    public int saveFile(MultipartFile files, int boardId) throws IOException {
        if (files.isEmpty()) {
            return 0;
        }

        String orignName = files.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension = orignName.substring(orignName.lastIndexOf("."));
        String savedName = uuid + extension;
        String savedPath = fileDir + savedName;

        FileEntity file = FileEntity.builder()
                .orgNm(orignName)
                .savedNm(savedName)
                .savedPath(savedPath)
                .boardId(boardId)
                .build();

        files.transferTo(new File(savedPath));

        int savedFile = fileDao.save(file);

        return savedFile;
    }

}
