package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

public class DealNotFoundException extends RuntimeException{

    public DealNotFoundException(Long id){
        super(String.format(ExceptionMessage.DEAL_NOT_FOUND.getMessage(), id));
    }
}
