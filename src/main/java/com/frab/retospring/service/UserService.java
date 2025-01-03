package com.frab.retospring.service;

import com.frab.retospring.dto.UserGetResponse;
import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.model.User;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest);

    UserGetResponse getByEmail(String email);

    List<UserGetResponse> getAll();

}
