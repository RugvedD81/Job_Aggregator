package com.example.Auth_Service.controller;

import com.example.Auth_Service.entity.Jobs;
import com.example.Auth_Service.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login/success")
    public ResponseEntity<List<Jobs>> loginSuccess(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        authService.registerUser(oAuth2User);

        List<Jobs> jobs = authService.fetchAllJobs();

        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(authService.forgotPassword(email));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, null);
        return ResponseEntity.ok("Logged out successfully.");
    }

    @GetMapping("/jobs/all")
    public ResponseEntity<List<Jobs>> getAllJobs() {
        return ResponseEntity.ok(authService.fetchAllJobs());
    }

    @GetMapping("/jobs/search")
    public ResponseEntity<List<Jobs>>getByKeyword(@RequestParam String keyword){
        return ResponseEntity.ok(authService.fetchByKeyword(keyword));
    }
    
}
