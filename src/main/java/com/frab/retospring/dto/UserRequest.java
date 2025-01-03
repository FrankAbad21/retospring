package com.frab.retospring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.frab.retospring.constants.ErrorConstant;
import com.frab.retospring.model.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {

    @JsonIgnore
    @Value("${app.email.regexp}")
    private static final String REGEXP_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @JsonIgnore
    @Value("${app.password.regexp}")
    private static final String REGEXP_PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    @NotNull(message = ErrorConstant.NAME_NOT_NULL)
    @NotBlank(message = ErrorConstant.NAME_NOT_BLANK)
    private String name;

    @NotNull(message = ErrorConstant.EMAIL_NOT_NULL)
    @NotBlank(message = ErrorConstant.EMAIL_NOT_BLANK)
    @Email(message = ErrorConstant.EMAIL_NOT_VALID,
            regexp = REGEXP_EMAIL ,
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    @NotNull(message = ErrorConstant.PASSWORD_NOT_NULL)
    @NotBlank(message = ErrorConstant.PASSWORD_NOT_BLANK)
    @Pattern(regexp = REGEXP_PASSWORD, message = ErrorConstant.PASSWORD_NOT_VALID)
    private String password;

    private List<Phone> phones;

}
