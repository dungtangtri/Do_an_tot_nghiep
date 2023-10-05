package com.dung.spring.application.security.services;

import com.dung.spring.application.payload.UserProfileInterface;
import com.dung.spring.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<UserProfileInterface> getUserProfile(
            @AuthenticationPrincipal UserDetailsImpl user){
        Long userId = user.getId();
        return userRepository.getUserProfileById(userId);
    }

}
