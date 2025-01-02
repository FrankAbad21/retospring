package com.frab.retospring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frab.retospring.model.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
public class UserRequest {

    @JsonIgnore
    @Value("${app.email.regexp}")
    private String regexpEmail;

    @NotNull(message = "EL campo name no puede ser nulo")
    @NotBlank(message = "EL campo name no puede estar vacio")
    private String name;

    @NotNull(message = "EL campo email no puede ser nulo")
    @NotBlank(message = "EL campo email no puede estar vacio")
    @Email(message = "El email no es valido",
            regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull(message = "EL campo password no puede ser nulo")
    @NotBlank(message = "EL campo password no puede estar vacio")
    private String password;

    private List<Phone> phones;

}
