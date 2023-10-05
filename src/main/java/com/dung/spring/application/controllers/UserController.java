package com.dung.spring.application.controllers;

import com.dung.spring.application.payload.UserProfileInterface;
import com.dung.spring.application.security.services.UserDetailsImpl;
import com.dung.spring.application.security.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@Data
public class UserController {
    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_USER')")
    @GetMapping("/get_user_profile")
    public ResponseEntity<List<UserProfileInterface>> getUserProfile(@AuthenticationPrincipal UserDetailsImpl user){
        return ResponseEntity.ok().body(userService.getUserProfile(user));
    }
}
