package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;
import ru.jdbcfighters.renthub.domain.models.enums.Status;

public class AttributeNotFoundException extends RuntimeException{
    public AttributeNotFoundException(Long id){
        super(String.format(ExceptionMessage.ATTRIBUTE_NOT_FOUND.getMessage(), id));
    }
}
