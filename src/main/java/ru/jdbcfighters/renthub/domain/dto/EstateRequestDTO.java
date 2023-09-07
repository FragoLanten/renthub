package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.INTEGER_PATTERN;
import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.STRING_PATTERN;

@Builder
public record EstateRequestDTO(
        @NotBlank(message = "Number doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits")
        String number,
        @NotBlank(message = "Square doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits and dot")
        String square,
        @NotBlank(message = "Price doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits and dot")
        String price,
        @NotBlank(message = "Street doesn't empty!")
        String street,
        @NotBlank(message = "City doesn't empty!")
        @Pattern(regexp = STRING_PATTERN, message = "Only letters and -")
        String city,
        @Pattern(regexp = STRING_PATTERN, message = "Only letters and -")
        String typeEstate,
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits")
        String flatNumber,
        @NotBlank(message = "Number of floor doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits")
        String numberOfFloors,
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits")
        String floor,
        @NotBlank(message = "Number of rooms doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "Only digits")
        String numberOfRooms,
        String balcony) {
}


