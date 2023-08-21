package ru.jdbcfighters.renthub.services.impl;

import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.repositories.AttrubuteValueRepository;
import ru.jdbcfighters.renthub.services.AttributeValueService;

import java.util.List;

@Service
public class AttributeValueServerImpl implements AttributeValueService {

    private final AttrubuteValueRepository attrubuteValueRepository;

    public AttributeValueServerImpl(AttrubuteValueRepository attrubuteValueRepository) {
        this.attrubuteValueRepository = attrubuteValueRepository;
    }

    @Override
    public AttributeValue findById(Long id) {
        return attrubuteValueRepository.findById(id).orElseThrow(() -> new RuntimeException("Attribute value not found!"));
    }

    @Override
    public List<AttributeValue> findAll() {
        return attrubuteValueRepository.findAll();
    }

    @Override
    public AttributeValue create(AttributeValue object) {
        return attrubuteValueRepository.save(object);
    }

    @Override
    public AttributeValue update(AttributeValue object) {
//        if (!attrubuteRepository.existsById(object.getId()))
//            throw new EntityNotFoundException("Subscription not found!");
        return attrubuteValueRepository.save(object);
    }

    @Override
    public void delete(Long id) {
//        if (!attrubuteRepository.existsById(id))
//            throw new EntityNotFoundException("Subscription not found!");
        attrubuteValueRepository.deleteAttributeValue(id);
    }
}
