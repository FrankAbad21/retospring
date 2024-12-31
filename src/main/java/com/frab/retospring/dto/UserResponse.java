package com.frab.retospring.dto;

import com.frab.retospring.model.Phone;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserResponse {

    private UUID uuid;
    private String name;
    private String email;
    private String password;

    private List<Phone> phones;

    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean active;

}
