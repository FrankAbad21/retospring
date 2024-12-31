package com.frab.retospring.service;

import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.model.User;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    UserResponse getByEmail(String email);

    List<UserResponse> getAll();

}
