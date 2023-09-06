package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.exception.AttributeNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Attribute;
import ru.jdbcfighters.renthub.repositories.AttributeRepository;
import ru.jdbcfighters.renthub.services.impl.AttributeServerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AttributeImplTest {
    @InjectMocks
    private AttributeServerImpl attributeServer;

    @Mock
    private AttributeRepository attributeRepository;
    private static final Long ATTRIBUTE_ID = 1L;

    @Test
    void finByIdTest() {
        Attribute attributeValue = new Attribute();
        when(attributeRepository.findById(ATTRIBUTE_ID)).thenReturn(Optional.of(attributeValue));
        final Attribute actualAttributeValue = attributeServer.findById(ATTRIBUTE_ID);

        Assertions.assertNotNull(actualAttributeValue);
        assertEquals(actualAttributeValue, attributeValue);
    }

    @Test
    void findAllTest() {
        List<Attribute> attributeValues = new ArrayList<>();
        when(attributeRepository.findAll()).thenReturn(attributeValues);
        List<Attribute> actualAttributeValues = attributeServer.findAll();

        Assertions.assertNotNull(actualAttributeValues);
        assertEquals(actualAttributeValues, attributeValues);
    }

    @Test
    void saveTest() {
        Attribute attributeValue = new Attribute();
        attributeRepository.save(attributeValue);
        when(attributeRepository.save(attributeValue)).thenReturn(attributeValue);
        Attribute actualAttributeValue = attributeServer.save(attributeValue);

        Assertions.assertNotNull(actualAttributeValue);
        assertEquals(actualAttributeValue, attributeValue);
//        verify(attrubuteRepository).save(attributeValue);
    }

    @Test
    void updateTest() {
        Attribute attributeValue = new Attribute();
        Assertions.assertThrows(AttributeNotFoundException.class, () ->
                attributeServer.update(attributeValue));
    }

    @Test
    void deleteTest() {
        Assertions.assertThrows(AttributeNotFoundException.class, () ->
                attributeServer.delete(ATTRIBUTE_ID));
    }
}
