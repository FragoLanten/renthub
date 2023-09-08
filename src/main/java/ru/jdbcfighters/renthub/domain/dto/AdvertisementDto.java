package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static ru.jdbcfighters.renthub.domain.dto.validation.ValidationConstants.INTEGER_PATTERN;

@Builder
public record AdvertisementDto(
        @NotBlank(message = "Field doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "only positive numbers")
        String amountOfDays,
        @NotBlank(message = "Field doesn't empty!")
        @Pattern(regexp = INTEGER_PATTERN, message = "only positive numbers")
        String rank
)
{
}
