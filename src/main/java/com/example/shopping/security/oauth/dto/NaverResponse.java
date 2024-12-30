package com.example.shopping.security.oauth.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return this.attribute.get("email").toString();
    }

    @Override
    public String getMobile() { return this.attribute.get("mobile").toString(); }


}