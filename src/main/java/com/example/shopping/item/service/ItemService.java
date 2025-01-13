package com.example.shopping.item.service;

import com.example.shopping.category.entity.Category;
import com.example.shopping.category.repository.CategoryRepository;
import com.example.shopping.file.entity.File;
import com.example.shopping.file.service.FileService;
import com.example.shopping.global.exception.CustomException;
import com.example.shopping.item.dto.ItemForm;
import com.example.shopping.item.entity.Item;
import com.example.shopping.item.repository.ItemRepository;
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
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileService fileService;

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public void saveItem(ItemForm itemForm, List<MultipartFile> images) {
        // 1. 카테고리 유효성 검사
        Category category = categoryRepository.findById(itemForm.getCategory_id())
                .orElseThrow(() -> new CustomException("존재하지 않는 카테고리입니다."));
        // 2. 이미지 유효성 검사
        validImages(images);

        // 3. 파일 엔티티 생성 및 저장
        List<File> files = fileService.saveFiles(images);

        // 4. Item 엔티티 생성
        Item item = Item.builder()
                .name(itemForm.getName())
                .category(category)
                .price(itemForm.getPrice())
                .count(itemForm.getCount())
                .content(itemForm.getContent())
                .build();

        // 5. Item 엔티티에 파일 엔티티 연관 추가
        item.addImages(files);

        // 6. Item 저장
        itemRepository.save(item);
    }

    public List<Item> findByCategory_id(Long category_id) {
        categoryRepository.findById(category_id).orElseThrow(() -> new CustomException("존재하지 않는 카테고리입니다."));
        return itemRepository.findByCategory_id(category_id);
    }

    private void validImages(List<MultipartFile> images) {
        if (images.isEmpty() || images.size() > 5) {
            throw new CustomException("이미지는 1개 이상 5개 이하로 업로드해야 합니다.");
        }

        List<String> allowedExtensions = List.of(".jpg", ".jpeg", ".png");
        for (MultipartFile image : images) {
            String originalName = image.getOriginalFilename();
            String extension = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();

            if (!allowedExtensions.contains(extension)) {
                throw new CustomException("허용되지 않는 파일 확장자입니다: " + extension);
            }

            if (!image.getContentType().startsWith("image/")) {
                throw new CustomException("이미지 파일만 업로드할 수 있습니다.");
            }
        }
    }
}
