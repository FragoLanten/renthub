package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.AttributeValue;

import java.util.List;

public interface AttributeValueService {
    AttributeValue findById(Long id);

    List<AttributeValue> findAll();

    AttributeValue save(AttributeValue object);

    AttributeValue update(AttributeValue object);

    void delete(Long id);
}
