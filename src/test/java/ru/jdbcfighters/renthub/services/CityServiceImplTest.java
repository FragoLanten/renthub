package ru.jdbcfighters.renthub.services;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.repositories.CityRepository;
import ru.jdbcfighters.renthub.services.impl.CityServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    private static final Long CITY_ID = 1L;


    @Test
    void getCityTest() {
        City city = new City();
        when(cityRepository.findById(CITY_ID)).thenReturn(Optional.of(city));

        City actualResult = cityService.getCity(CITY_ID);

        assertEquals(city, actualResult);

    }
    @Test
    void saveTest() {
        City city = new City();
        cityService.save(city);
        verify(cityRepository).save(eq(city));
    }
    @Test
    void deleteTest() {
        cityService.delete(CITY_ID);
        verify(cityRepository).deleteById(eq(CITY_ID));
    }

    @Test
    void findAllTest() {
        List<City> cityList = new ArrayList<>();
        when(cityRepository.findAll()).thenReturn(cityList);

        List<City> actualResult = cityService.findAll();

        assertEquals(cityList, actualResult);

    }
}