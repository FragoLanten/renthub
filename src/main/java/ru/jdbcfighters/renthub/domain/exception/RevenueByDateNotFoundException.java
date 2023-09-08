package ru.jdbcfighters.renthub.domain.exception;

import ru.jdbcfighters.renthub.domain.models.enums.ExceptionMessage;

import java.time.LocalDate;

public class RevenueByDateNotFoundException extends RuntimeException {
    public RevenueByDateNotFoundException(LocalDate localDate){
        super("Поступлений в эту дату не было");
    }
}
