package com.rootable.libraryservice2022.web.file;

import com.rootable.libraryservice2022.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir;

    public String getFileDir() {
        return fileDir;
    }

    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    /*//여러 파일 저장
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile)); //저장된 업로드 파일을 List에 추가
            }
        }
        return storeFileResult;
    }*/

    /*//파일 -> 서버 (저장/업로드)
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename(); //고객이 업로드한 파일명
        String storeFileName = createStoreFileName(originalFilename); //서버 저장 파일명
        multipartFile.transferTo(new File(getFullPath(storeFileName))); //파일 저장
        return new UploadFile(originalFilename, storeFileName);

    }*/

    //서버 파일 저장 형식 (uuid.확장자)
    public String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename); //확장자
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    public String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
