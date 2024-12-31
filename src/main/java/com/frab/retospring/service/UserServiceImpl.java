package com.frab.retospring.service;

import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.exception.exception.RequestException;
import com.frab.retospring.mapper.UserMapper;
import com.frab.retospring.model.User;
import com.frab.retospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User UserforEmail = userRepository.getByEmail(userRequest.getEmail());
        if(UserforEmail != null) throw new RequestException("El email ya existe", "P400");
        User user = UserMapper.UserRequestToUser(userRequest);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setToken("");
        return UserMapper.UserToUserResponse((User)userRepository.save(user));
    }


}
