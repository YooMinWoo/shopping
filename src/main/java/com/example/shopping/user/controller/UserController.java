package com.example.shopping.user.controller;

import com.example.shopping.global.dto.ApiResponse;
import com.example.shopping.user.dto.UserForm;
import com.example.shopping.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/userform-check")
    public ResponseEntity<?> UserFormCheck(@Valid @RequestBody UserForm userForm){
        return ResponseEntity.ok(ApiResponse.success("유저 유효성 성공!", userForm));
    }
}
