package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
public record AdvertisementDto(

        @NotNull(message = "Day amount must be positive")
        @Min(value = 1)
        Integer amountOfDays,

        @NotNull(message = "Promotion must be positive")
        @Min(value = 1)
        Integer rank
)
{
}
