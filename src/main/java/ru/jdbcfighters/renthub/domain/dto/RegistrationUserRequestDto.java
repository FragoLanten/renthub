package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.FIRST_NAME_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.LAST_NAME_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.LOGIN_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.PASSWORD_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.PHONE_PATTERN;

@Builder
public record RegistrationUserRequestDto(

        @NotBlank(message = "Login doesn't empty!")
        @Pattern(regexp = LOGIN_PATTERN, message = "Invalid login")
        String login,

        @NotBlank(message = "Password doesn't empty!")
        @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain only Latin letters and at least one digit, " +
                "one upper case letter, one lower case letter and one special symbol (“@#$%”)")
        String password,


        @NotBlank(message = "Phone doesn't empty!")
        @Pattern(regexp = PHONE_PATTERN, message = "Invalid phone")
        String phoneNumber,

        @NotBlank(message = "First name doesn't empty!")
        @Pattern(regexp = FIRST_NAME_PATTERN, message = "Invalid first name! First Name must contain only Cyrillic letters!")
        String firstName,

        @NotBlank(message = "Last name doesn't empty!")
        @Pattern(regexp = LAST_NAME_PATTERN, message = "Invalid last name! Last Name must contain only Cyrillic letters!")
        String lastName){

}
