package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Estate;

import java.util.List;

public interface EstateService {
    List<Estate> getAll();
    Estate getById(long id);
    Estate save(Estate estate);
    Estate update(long id, Estate updatedEstate);
    void delete(long id);
}
