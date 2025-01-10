package com.example.shopping.item.dto;

import com.example.shopping.category.entity.Category;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;


@Setter
@Getter
@ToString
public class ItemForm {

    @NotNull
    private Long category_id;

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private Long count;

    @NotBlank
    private String content;

}
