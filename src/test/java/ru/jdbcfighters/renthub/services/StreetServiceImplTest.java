package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.repositories.StreetRepository;
import ru.jdbcfighters.renthub.services.impl.StreetServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StreetServiceImplTest {


    @Mock
    private StreetRepository streetRepository;

    @InjectMocks
    private StreetServiceImpl streetService;

    private static final Long STREET_ID = 1L;

    @Test
    void saveTest() {
        Street street = new Street();
        streetService.save(street);
        verify(streetRepository).save(eq(street));
    }

    @Test
    void getStreetTest() {
        Street street = new Street();
        when(streetRepository.findById(STREET_ID)).thenReturn(Optional.of(street));

        Street actualResult = streetService.getStreet(STREET_ID);

        assertEquals(street, actualResult);

    }

    @Test
    void deleteTest() {
        streetService.delete(STREET_ID);
        verify(streetRepository).deleteById(eq(STREET_ID));
    }

    @Test
    void findAllTest() {
        List<Street> streetList = new ArrayList<>();
        when(streetRepository.findAll()).thenReturn(streetList);

        List<Street> actualResult = streetService.findAll();

        assertEquals(streetList, actualResult);
    }
}