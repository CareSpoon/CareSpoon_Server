package com.carespoon.service;

import com.carespoon.Repository.UserRepository;
import com.carespoon.domain.User;
import com.carespoon.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public void save(UserSaveRequestDto requestDto){
        userRepository.save(requestDto.toEntity());
    }
    public User findByUuid(UUID uuid) {
        User user = userRepository.findByUuid(uuid);
        return user;
    }
}
