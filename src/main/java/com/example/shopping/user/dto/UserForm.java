package com.example.shopping.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$",
            message = "아이디는 영문과 숫자를 조합하여 6자 이상 12자 이하로 입력해야 합니다."
    )
    private String username;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
            message = "패스워드는 영문, 숫자, 특수문자를 조합하여 8자 이상 20자 이하로 입력해야 합니다."
    )
    private String password;

    @NotBlank
    @Pattern(
            regexp = "^[가-힣]{2,5}$",
            message = "이름은 한글로 2글자 이상 5글자 이하로 입력해야 합니다."
    )
    private String name;

}
