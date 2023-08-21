package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;
import ru.jdbcfighters.renthub.domain.models.enums.Status;

public class DealStatusNotFoundException extends RuntimeException{

    public DealStatusNotFoundException(Status dealStatus){
        super(String.format(ExceptionMessage.STATUS_DEAL_NOT_FOUND.getMessage(), dealStatus.toString()));
    }
}
