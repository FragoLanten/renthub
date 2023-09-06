package ru.jdbcfighters.renthub.domain.models.enums;

import lombok.Getter;

public enum ExceptionMessage {

    DEAL_NOT_FOUND("Сделка с id %s не зарегистрирована"),
    TYPE_DEAL_NOT_FOUND("Тип сделки: %s не найден"),
    STATUS_DEAL_NOT_FOUND("Статус сделки: %s не найден"),
    ESTATE_NOT_FOUND("Недвижимость с id %s не зарегистрирована"),
    REVENUE_NOT_FOUND("Поступление с id %s не найдено"),
    ATTRIBUTE_NOT_FOUND("Аттрибут с id %s не найден"),
    ATTRIBUTE_VALUE_NOT_FOUND("Значение аттрибута с id %s не найдено");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
