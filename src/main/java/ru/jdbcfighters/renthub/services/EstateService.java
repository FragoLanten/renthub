package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface EstateService {
    List<Estate> getAll();
    Optional<Estate> getById(long id);
    Estate create(Estate estate);
    Estate update(long id, Estate updatedEstate);
    void delete(long id);
}
