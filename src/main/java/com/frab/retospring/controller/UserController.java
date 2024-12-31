package com.frab.retospring.controller;

import com.frab.retospring.dto.UserRequest;
import com.frab.retospring.dto.UserResponse;
import com.frab.retospring.exception.exception.RequestException;
import com.frab.retospring.service.UserService;
import com.frab.retospring.validation.ObjectValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    @PostMapping
    public ResponseEntity<UserResponse> crear(@RequestBody UserRequest userRequest){
        objectValidator.validate(userRequest);
        return new ResponseEntity<UserResponse>(userService.create(userRequest), HttpStatus.OK);
    }


}
