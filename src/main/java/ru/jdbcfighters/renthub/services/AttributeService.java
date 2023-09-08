package ru.jdbcfighters.renthub.services;


import ru.jdbcfighters.renthub.domain.models.Attribute;

import java.util.List;

public interface AttributeService {
    Attribute findById(Long id);

    List<Attribute> findAll();

    Attribute save(Attribute object);

    Attribute update(Attribute object);

    void delete(Long id);
}
