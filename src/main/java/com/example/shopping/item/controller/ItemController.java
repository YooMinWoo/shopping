package com.example.shopping.item.controller;

import com.example.shopping.global.dto.ApiResponse;
import com.example.shopping.item.dto.ItemForm;
import com.example.shopping.item.entity.Item;
import com.example.shopping.item.repository.ItemRepository;
import com.example.shopping.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

//    @GetMapping("/item")
//    public ResponseEntity<?> findAllItem(){
//        List<Item> items = itemService.findAll();
//        return ResponseEntity.ok(ApiResponse.success("상품리스트 전체 조회", items));
//    }

    @GetMapping("/item/{category_id}")
    public ResponseEntity<?> findItem(@PathVariable("category_id") Long category_id){
        List<Item> items = itemService.findByCategory_id(category_id);
        return ResponseEntity.ok(ApiResponse.success("상품리스트 조회", items));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/item", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveItem(@Valid ItemForm itemForm,
                                      @RequestPart("images") List<MultipartFile> images){
        itemService.saveItem(itemForm, images);
        System.out.println(itemForm.toString());
        System.out.println(images);
        return ResponseEntity.ok(ApiResponse.success("상품 추가 완료", null));
    }

}
