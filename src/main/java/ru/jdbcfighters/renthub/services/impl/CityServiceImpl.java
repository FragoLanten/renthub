package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.repositories.CityRepository;
import ru.jdbcfighters.renthub.services.CityService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }

    public City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("Город не найден!"));
    }

    public void delete(Long cityID) {
        cityRepository.deleteById(cityID);
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

}
