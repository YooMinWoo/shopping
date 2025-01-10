package com.example.shopping.item.service;

import com.example.shopping.category.entity.Category;
import com.example.shopping.category.repository.CategoryRepository;
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
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public void saveItem(ItemForm itemForm, List<MultipartFile> images){
        Category category = categoryRepository.findById(itemForm.getCategory_id()).orElseThrow(() -> new CustomException("존재하지 않는 카테고리입니다."));
        if(validImages(images)) throw new CustomException("첨부파일 에러 발생");

        fileUpload(images); // 파일 저장

        Item item = Item.builder()
                .name(itemForm.getName())
                .category(category)
                .price(itemForm.getPrice())
                .count(itemForm.getCount())
                .content(itemForm.getContent())
                .build();

        itemRepository.save(item);
    }

    public List<Item> findByCategory_id(Long category_id) {
        categoryRepository.findById(category_id).orElseThrow(() -> new CustomException("존재하지 않는 카테고리입니다."));
        return itemRepository.findByCategory_id(category_id);
    }

    public boolean validImages(List<MultipartFile> images){
        if(images.size() >= 1 && images.size() <= 5) return false;
        for(MultipartFile image : images){
            if(!image.getContentType().startsWith("image/")) return false;
        }
        return true;
    }

    public void fileUpload(List<MultipartFile> images){
        for(MultipartFile image : images){

            // 로컬에 저장하는 예
            String fileName = UUID.randomUUID().toString();
            Path path = Paths.get(FILE_DIRECTORY + fileName);

            try {
//                Files.createDirectories(path.getParent()); // 디렉토리 생성
                image.transferTo(path.toFile());           // 파일 저장
            } catch (IOException e) {
                throw new RuntimeException("파일 저장 중 오류 발생", e);
            }
        }
    }
}
