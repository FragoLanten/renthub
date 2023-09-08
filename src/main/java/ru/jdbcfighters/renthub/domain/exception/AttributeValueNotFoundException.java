package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

public class AttributeValueNotFoundException  extends RuntimeException{
    public AttributeValueNotFoundException(Long id){
        super(String.format(ExceptionMessage.ATTRIBUTE_VALUE_NOT_FOUND.getMessage(), id));
    }
}
