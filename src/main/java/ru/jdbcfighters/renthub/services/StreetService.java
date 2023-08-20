package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Street;

import java.util.List;

public interface StreetService {
    public Street save(Street street);

    public Street getStreet(Long streetId);

    public void delete(Long streetID);

    public List<Street> findAll();

}