package com.frab.retospring.service;

import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.exception.exception.RequestException;
import com.frab.retospring.mapper.UserMapper;
import com.frab.retospring.model.User;
import com.frab.retospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse create(UserRequest userRequest) {
        User UserforEmail = userRepository.getByEmail(userRequest.getEmail());
        if(UserforEmail != null) throw new RequestException("El email ya existe", "P400");
        //Realiza el encriptado
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User user = UserMapper.UserRequestToUser(userRequest);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setToken("");
        return UserMapper.UserToUserResponse((User)userRepository.save(user));
    }

    @Override
    public UserResponse getByEmail(String email) {
        return UserMapper.UserToUserResponse(userRepository.findUserByEmail(email).orElseThrow());
    }

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(UserMapper::UserToUserResponse).collect(Collectors.toList());
    }


}
