package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;
import ru.jdbcfighters.renthub.domain.models.enums.Type;

public class DealTypeNotFoundException extends RuntimeException{

    public DealTypeNotFoundException(Type dealType){
        super(String.format(ExceptionMessage.TYPE_DEAL_NOT_FOUND.getMessage(), dealType.toString()));
    }
}
