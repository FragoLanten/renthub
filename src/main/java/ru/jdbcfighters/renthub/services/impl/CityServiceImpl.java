package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.repositories.CityRepository;

import java.util.List;

@Service
public class CityServiceImpl {
    @Autowired
    CityRepository cityRepository;

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City getCity(Long cityId) {
        return cityRepository.getReferenceById(cityId);
    }

    public void deleteCity(Long cityID) {
        cityRepository.deleteById(cityID);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
