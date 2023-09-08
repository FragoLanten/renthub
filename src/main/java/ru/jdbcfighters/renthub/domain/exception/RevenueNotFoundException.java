package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

public class RevenueNotFoundException extends RuntimeException {
    public RevenueNotFoundException(Long id){
        super(String.format(ExceptionMessage.REVENUE_NOT_FOUND.getMessage(), id));
    }
}
