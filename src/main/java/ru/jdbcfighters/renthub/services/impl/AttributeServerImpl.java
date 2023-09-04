package ru.jdbcfighters.renthub.services.impl;

import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.exception.AttributeNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Attribute;
import ru.jdbcfighters.renthub.repositories.AttrubuteRepository;
import ru.jdbcfighters.renthub.services.AttributeService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AttributeServerImpl implements AttributeService {
    private final AttrubuteRepository attrubuteRepository;

    public AttributeServerImpl(AttrubuteRepository attrubuteRepository) {
        this.attrubuteRepository = attrubuteRepository;
    }

    @Override
    public Attribute findById(Long id) {
        return attrubuteRepository.findById(id).orElseThrow(() -> new AttributeNotFoundException(id));
    }

    @Override
    public List<Attribute> findAll() {
        return attrubuteRepository.findAll();
    }

    @Override
    public Attribute save(Attribute object) {
        return attrubuteRepository.save(object);
    }

    @Override
    public Attribute update(Attribute object) {
        existCheck(object.getId());
        return attrubuteRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        existCheck(id);
        attrubuteRepository.deleteAttribute(id);
    }

    private void existCheck(Long id) {
        if (!attrubuteRepository.existsById(id))
            throw new AttributeNotFoundException(id);
    }

}
