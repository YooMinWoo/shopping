package com.example.shopping.category.controller;

import com.example.shopping.category.dto.CategoryForm;
import com.example.shopping.category.entity.Category;
import com.example.shopping.category.service.CategoryService;
import com.example.shopping.global.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> findAllCategory(){
        List<Category> categories = categoryService.findAllCategory();
        return ResponseEntity.ok(ApiResponse.success("카테고리 리스트 전체 조회", categories));
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveCategory(@Valid CategoryForm categoryForm){
        categoryService.saveCategory(categoryForm);
        return ResponseEntity.ok(ApiResponse.success("카테고리 저장 성공", null));
    }
}
