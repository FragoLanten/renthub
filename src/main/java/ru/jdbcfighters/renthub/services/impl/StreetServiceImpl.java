package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.repositories.StreetReository;

import java.util.List;

@Service
public class StreetServiceImpl {
    @Autowired
    StreetReository streetReository;

    public Street saveStreet(Street street) {
        return streetReository.save(street);
    }

    public Street getStreet(Long streetId) {
        return streetReository.getReferenceById(streetId);
    }

    public void deleteStreet(Long streetID) {
        streetReository.deleteById(streetID);
    }

    public List<Street> findAll() {
        return streetReository.findAll();
    }

}
