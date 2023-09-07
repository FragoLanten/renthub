package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jdbcfighters.renthub.domain.exception.EstateNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.services.EstateService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EstateServiceImpl implements EstateService {

    private final EstateRepo estateRepo;

    @Autowired
    public EstateServiceImpl(EstateRepo estateRepo) {
        this.estateRepo = estateRepo;
    }

    @Override
    public List<Estate> getAll() {
        return estateRepo.findAll();
    }

    @Override
    public Estate getById(long id) {
        Optional<Estate> optEst = estateRepo.findById(id);
        if (optEst.isPresent()){
            return optEst.get();
        }
        throw new EstateNotFoundException(id);
    }

    @Override
    @Transactional
    public Estate save(Estate estate) {
        return estateRepo.save(estate);
    }

    @Override
    @Transactional
    public Estate update(long id, Estate updatedEstate) {
        updatedEstate.setId(id);
        return estateRepo.save(updatedEstate);
    }

    @Override
    @Transactional
    public void delete(long id) {
        estateRepo.deleteById(id);
    }
}
