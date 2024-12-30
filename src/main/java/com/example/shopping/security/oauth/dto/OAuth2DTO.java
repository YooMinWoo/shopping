package com.example.shopping.security.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OAuth2DTO {
    private Long id;
    private Long user_id;
    private String email;
    private String provider;
}
