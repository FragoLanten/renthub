package ru.jdbcfighters.renthub.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.LOGIN_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.PASSWORD_PATTERN;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthRequestDTO {

    @NotBlank(message = "Login doesn't empty!")
    @Pattern(regexp = LOGIN_PATTERN, message = "Invalid login")
    private String login;

    @NotBlank(message = "Password doesn't empty!")
    private String password;

}
