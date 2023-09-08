package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

public class RevenueException extends RuntimeException{

    public RevenueException() {
        super(String.format(ExceptionMessage.REVENUE_EXCEPTION.getMessage()));
    }
}
