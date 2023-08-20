package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Street;

import java.util.List;

public interface StreetService {
    public Street saveStreet(Street street);

    public Street getStreet(Long streetId);

    public void deleteStreet(Long streetID);

    public List<Street> findAll();

}