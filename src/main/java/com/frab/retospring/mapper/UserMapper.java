package com.frab.retospring.mapper;

import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.model.Phone;
import com.frab.retospring.model.User;

public class UserMapper {

    public static UserResponse UserToUserResponse(User user) {
        UserResponse userResponse =  UserResponse
                .builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .phones(user.getPhones())
                .created(user.getCreated())
                .modified(user.getModified())
                .token(user.getToken())
                .active(user.isActive())
                .lastLogin(user.getLastLogin())
                .build();
        return userResponse;
    }

    public static User UserRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phones(userRequest.getPhones())
                .build();

    }


}
