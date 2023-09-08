package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.repositories.StreetRepository;
import ru.jdbcfighters.renthub.services.StreetService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {
    @Autowired
    StreetRepository streetRepository;

    public Street save(Street street) {
        return streetRepository.save(street);
    }

    public Street getStreet(Long streetId) {
        return streetRepository.findById(streetId).orElseThrow(() -> new EntityNotFoundException("Улица не найдена!"));
    }

    public void delete(Long streetID) {
        streetRepository.deleteById(streetID);
    }

    public List<Street> findAll() {
        return streetRepository.findAll();
    }

}
