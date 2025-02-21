package com.example.shopping.security.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
@RequiredArgsConstructor
public class CustomClientRegistrationRepo {

    private final SocialClientRegistration socialClientRegistration;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        return new InMemoryClientRegistrationRepository(
                socialClientRegistration.naverClientRegistration(),
                socialClientRegistration.googleClientRegistration()
        );
    }

}
