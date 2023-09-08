package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;
import ru.jdbcfighters.renthub.domain.models.enums.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.FIRST_NAME_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.LAST_NAME_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.PHONE_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.LOGIN_PATTERN;

@Builder
public record UserRequestDto(
        @NotBlank(message = "Login doesn't empty!")
        @Size(max = 30, message = "Login length must be up to 30 characters")
        @Pattern(regexp = LOGIN_PATTERN, message = "Only Latin letters are allowed")
        String login,

        @NotBlank(message = "First name doesn't empty!")
        @Pattern(regexp = FIRST_NAME_PATTERN, message = "Invalid first name")
        String firstName,

        @NotBlank(message = "Last name doesn't empty!")
        @Pattern(regexp = LAST_NAME_PATTERN, message = "Invalid last name")
        String lastName,

        @NotBlank(message = "Phone doesn't empty!")
        @Pattern(regexp = PHONE_PATTERN, message = "Invalid phone")
        String phoneNumber,
        Role role) {
}
