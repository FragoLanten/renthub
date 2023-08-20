package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.City;

import java.util.List;

public interface CityService {
    public City saveCity(City city);

    public City getCity(Long cityId);

    public void deleteCity(Long cityID);

    public List<City> findAll();

}

