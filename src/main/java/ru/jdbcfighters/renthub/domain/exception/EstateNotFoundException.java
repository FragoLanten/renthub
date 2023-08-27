package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

public class EstateNotFoundException extends RuntimeException{

    public EstateNotFoundException(Long id){
        super(String.format(ExceptionMessage.ESTATE_NOT_FOUND.getMessage(), id));
    }
}
