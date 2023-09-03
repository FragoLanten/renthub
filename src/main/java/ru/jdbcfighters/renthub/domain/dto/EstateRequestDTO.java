package ru.jdbcfighters.renthub.domain.dto;

import java.math.BigDecimal;

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


