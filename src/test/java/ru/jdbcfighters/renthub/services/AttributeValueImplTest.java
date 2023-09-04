package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.exception.AttributeValueNotFoundException;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.repositories.AttrubuteValueRepository;
import ru.jdbcfighters.renthub.services.impl.AttributeValueServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AttributeValueImplTest {
    @InjectMocks
    private AttributeValueServerImpl attributeValueServer;

    @Mock
    private AttrubuteValueRepository attrubuteValueRepository;
    private static final Long ATTRIBUTE_VALUE_ID = 1L;

    @Test
    void finByIdTest() {
        AttributeValue attributeValue = new AttributeValue();
        when(attrubuteValueRepository.findById(ATTRIBUTE_VALUE_ID)).thenReturn(Optional.of(attributeValue));
        final AttributeValue actualAttributeValue = attributeValueServer.findById(ATTRIBUTE_VALUE_ID);

        Assertions.assertNotNull(actualAttributeValue);
        assertEquals(actualAttributeValue, attributeValue);
    }

    @Test
    void findAllTest() {
        List<AttributeValue> attributeValues = new ArrayList<>();
        when(attrubuteValueRepository.findAll()).thenReturn(attributeValues);
        List<AttributeValue> actualAttributeValues = attributeValueServer.findAll();

        Assertions.assertNotNull(actualAttributeValues);
        assertEquals(actualAttributeValues, attributeValues);
    }

    @Test
    void saveTest() {
        AttributeValue attributeValue = new AttributeValue();
        attrubuteValueRepository.save(attributeValue);
        when(attrubuteValueRepository.save(attributeValue)).thenReturn(attributeValue);
        AttributeValue actualAttributeValue = attributeValueServer.save(attributeValue);

        Assertions.assertNotNull(actualAttributeValue);
        assertEquals(actualAttributeValue, attributeValue);
//        verify(attrubuteValueRepository).save(attributeValue);
    }

    @Test
    void updateTest() {
        AttributeValue attributeValue = new AttributeValue();
        Assertions.assertThrows(AttributeValueNotFoundException.class, () ->
                attributeValueServer.update(attributeValue));
    }

    @Test
    void deleteTest() {
        Assertions.assertThrows(AttributeValueNotFoundException.class, () ->
                attributeValueServer.delete(ATTRIBUTE_VALUE_ID));
    }
}
