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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> crear(@RequestBody UserRequest userRequest){
        objectValidator.validate(userRequest);
        return new ResponseEntity<UserResponse>(userService.create(userRequest), HttpStatus.OK);
    }

    // Find All
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<UserResponse>> findAll(){
        return new ResponseEntity<>(this.userService.getAll(), HttpStatus.OK);
    }

    // Find By Email
    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserResponse> findById(@PathVariable String email){
        return new ResponseEntity<>(this.userService.getByEmail(email), HttpStatus.OK);
    }


}
