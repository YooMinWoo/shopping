package com.example.shopping.category.service;

import com.example.shopping.category.dto.CategoryForm;
import com.example.shopping.category.entity.Category;
import com.example.shopping.category.repository.CategoryRepository;
import com.example.shopping.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public void saveCategory(CategoryForm categoryForm){
        if(categoryRepository.findByName(categoryForm.getName()) != null) throw new CustomException("이미 존재하는 카테고리입니다.");
        Category category = Category.builder()
                            .name(categoryForm.getName())
                            .build();
        categoryRepository.save(category);
    }
}
