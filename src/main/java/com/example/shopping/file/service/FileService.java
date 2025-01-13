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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {
    public static final String FILE_DIRECTORY = "C:\\Users\\CODELINE\\Desktop\\tmp\\test\\";

    public List<File> saveFiles(List<MultipartFile> images) {
        List<File> files = new ArrayList<>();

        for (MultipartFile image : images) {
            try {
                // 1. 파일 저장 이름 및 경로 생성
                String originalName = image.getOriginalFilename();
                String extension = originalName.substring(originalName.lastIndexOf("."));
                String storedName = UUID.randomUUID().toString() + extension;
                Path filePath = Paths.get(FILE_DIRECTORY, storedName);

                // 2. 로컬에 파일 저장
                Files.createDirectories(filePath.getParent());
                image.transferTo(filePath.toFile());

                // 3. File 엔티티 생성
                File file = File.builder()
                        .original_name(originalName)
                        .stored_name(storedName)
                        .file_path(filePath.toString())
                        .content_type(image.getContentType())
                        .size(image.getSize())
                        .build();

                files.add(file);

            } catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류 발생: " + e.getMessage(), e);
            }
        }

        return files;
    }
}
