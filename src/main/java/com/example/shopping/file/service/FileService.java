package com.example.shopping.file.service;

import com.example.shopping.file.entity.File;
import com.example.shopping.file.repository.FileRepository;
import com.example.shopping.item.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    private final String FILE_DIRECTORY = "C:\\Users\\CODELINE\\Desktop\\tmp\\test\\";
    private final FileRepository fileRepository; // 파일 관련 DB 작업

    public String saveFileToLocal(MultipartFile image) {
        String fileName = UUID.randomUUID().toString();
        Path path = Paths.get(FILE_DIRECTORY + fileName);

        try {
            image.transferTo(path.toFile());           // 파일 저장
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류 발생", e);
        }

        return fileName; // 저장된 파일 이름 반환
    }

    public void saveFileToDatabase(String fileName, Item item) {
        File fileEntity = File.builder()
                .fileName(fileName)
                .filePath(FILE_DIRECTORY + fileName)
                .item(item) // 파일과 아이템 연관
                .build();
        fileRepository.save(fileEntity); // 파일 정보 DB 저장
    }
}
