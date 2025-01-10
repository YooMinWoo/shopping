package com.example.shopping.user.controller;

import com.example.shopping.global.dto.ApiResponse;
import com.example.shopping.security.user.CustomUserDetails;
import com.example.shopping.user.dto.UserForm;
import com.example.shopping.user.entity.User;
import com.example.shopping.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/userform-check")
    public ResponseEntity<?> userFormCheck(@Valid @RequestBody UserForm userForm){
        return ResponseEntity.ok(ApiResponse.success("유저 유효성 성공!", userForm));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserForm userForm){
        userService.signup(userForm);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공!", userForm));
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> adminSignup(@Valid @RequestBody UserForm userForm){
        userService.adminSignup(userForm);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공!", userForm));
    }

    @PostMapping("/loginProcess")
    public ResponseEntity<?> login(@RequestParam("id") String id,
                                   @RequestParam("password") String password){
        return ResponseEntity.ok(ApiResponse.success("로그인 성공!", null));
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        return ResponseEntity.ok(ApiResponse.success("개인정보 조회", user));
    }

    @GetMapping("/")
    public ResponseEntity<?> main(){
        return ResponseEntity.ok(ApiResponse.success("메인페이지!", null));
    }
}
