package com.example.Auth_Service.service;

import com.example.Auth_Service.client.JobsClient;
import com.example.Auth_Service.entity.Auth;
import com.example.Auth_Service.entity.Jobs;
import com.example.Auth_Service.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JobsClient jobClient;

    // Register user after successful OAuth login
    public String registerUser(OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<Auth> existingUser = authRepository.findByEmail(email);

        if (existingUser.isPresent()) {
            return existingUser.get().getUsername();
        }

        Auth newUser = new Auth(email,name);
        newUser.setUsername(name);
        newUser.setEmail(email);
        authRepository.save(newUser);

        return name;
    }

    // Simple forgot password (placeholder)
    public String forgotPassword(String email) {
        Optional<Auth> user = authRepository.findByEmail(email);
        if (user.isPresent()) {
            return "Reset link sent to " + email;
        } else {
            return "No user registered with " + email;
        }
    }

    // Fetch jobs from crawler service via Feign client
    public List<Jobs> fetchAllJobs() {
        return jobClient.getAllJobs();
    }

    //fetch jobs from crawler service from keyword
    public List<Jobs> fetchByKeyword(String keyword){
        return jobClient.getByResponse(keyword);
    }

}
