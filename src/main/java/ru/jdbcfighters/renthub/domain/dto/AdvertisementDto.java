package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
public record AdvertisementDto(
        Long amountOfDays,
        Integer rank
)
{
}
