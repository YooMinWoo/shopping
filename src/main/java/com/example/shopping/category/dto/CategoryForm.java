package com.example.shopping.category.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {

    @NotBlank
    private String name;
}
