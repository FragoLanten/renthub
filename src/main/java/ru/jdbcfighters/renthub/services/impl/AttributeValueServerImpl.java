package ru.jdbcfighters.renthub.services.impl;

import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.exception.AttributeValueNotFoundException;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.repositories.AttrubuteValueRepository;
import ru.jdbcfighters.renthub.services.AttributeValueService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AttributeValueServerImpl implements AttributeValueService {

    private final AttrubuteValueRepository attrubuteValueRepository;

    public AttributeValueServerImpl(AttrubuteValueRepository attrubuteValueRepository) {
        this.attrubuteValueRepository = attrubuteValueRepository;
    }

    @Override
    public AttributeValue findById(Long id) {
        return attrubuteValueRepository.findById(id).orElseThrow(() -> new AttributeValueNotFoundException(id));
    }

    @Override
    public List<AttributeValue> findAll() {
        return attrubuteValueRepository.findAll();
    }

    @Override
    public AttributeValue save(AttributeValue object) {
        return attrubuteValueRepository.save(object);
    }

    @Override
    public AttributeValue update(AttributeValue object) {
        existCheck(object.getId());
        return attrubuteValueRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        existCheck(id);
        attrubuteValueRepository.deleteAttributeValue(id);
    }

    private void existCheck(Long id) {
        if (!attrubuteValueRepository.existsById(id))
            throw new AttributeValueNotFoundException(id);
    }
}
