package ru.jdbcfighters.renthub.domain.models.enums;

import lombok.Getter;

public enum ExceptionMessage {

    DEAL_NOT_FOUND("Сделка с id %s не зарегистрирована"),
    TYPE_DEAL_NOT_FOUND("Тип сделки: %s не найден"),
    STATUS_DEAL_NOT_FOUND("Статус сделки: %s не найден"),
    ESTATE_NOT_FOUND("Недвижимость с id %s не зарегистрирована"),
    REVENUE_NOT_FOUND("Поступление с id %s не найдено");

    @Getter
    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}
