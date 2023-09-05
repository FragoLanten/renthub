package ru.jdbcfighters.renthub.domain.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record EstateRequestDTO(Integer number,
                               Float square,
                               BigDecimal price,
                               String street,
                               String city,
                               String typeEstate,
                               String flatNumber,
                               String numberOfFloors,
                               String floor,
                               String numberOfRooms,
                               String balcony) {
}


