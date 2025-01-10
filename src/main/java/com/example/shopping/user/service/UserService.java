package com.example.shopping.user.service;

import com.example.shopping.global.exception.CustomException;
import com.example.shopping.user.dto.Role;
import com.example.shopping.user.dto.UserForm;
import com.example.shopping.user.entity.User;
import com.example.shopping.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserForm userForm){
        String username = userForm.getUsername();
        if(userRepository.findByUsername(username) != null) throw new CustomException("이미 존재하는 아이디입니다.");

        User user = User.builder()
                        .username(userForm.getUsername())
                        .password(passwordEncoder.encode(userForm.getPassword()))
                        .name(userForm.getName())
                        .role(Role.ROLE_USER).build();

        userRepository.save(user);
    }

    public void adminSignup(UserForm userForm){
        String username = userForm.getUsername();
        if(userRepository.findByUsername(username) != null) throw new CustomException("이미 존재하는 아이디입니다.");

        User user = User.builder()
                .username(userForm.getUsername())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .name(userForm.getName())
                .role(Role.ROLE_ADMIN).build();

        userRepository.save(user);
    }
}
