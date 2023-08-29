package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.exception.EstateNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.services.impl.EstateServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public final class EstateServiceImplTest {
    private static final long ID = 1L;
    @Mock
    private EstateRepo estateRepo;
    @InjectMocks
    private EstateServiceImpl estateService;

    @Test
    public void getAllShouldReturnEstates() {
        final List<Estate> estates = new ArrayList<Estate>();

        estates.add(new Estate());
        estates.add(new Estate());
        estates.add(new Estate());

        when(estateRepo.findAll()).thenReturn(estates);

        final List<Estate> actualEstates = estateRepo.findAll();

        Assertions.assertNotNull(actualEstates);
        Assertions.assertArrayEquals(estates.toArray(), actualEstates.toArray());
        verify(estateRepo).findAll();
    }

    @Test
    public void getByIdShouldReturnEstate() {
        final Estate estate = mock(Estate.class);
        when(estateRepo.findById(ID)).thenReturn(Optional.of(estate));

        final Estate actual = estateService.getById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(estate, actual);
        verify(estateRepo).findById(ID);
    }

    @Test
    public void getByIdShouldReturnException() {
        when(estateRepo.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(EstateNotFoundException.class, () ->
                estateService.getById(ID));
    }

    @Test
    public void saveShouldSaveEstate() {
        final Estate estate = mock(Estate.class);
        when(estateRepo.save(estate)).thenReturn(estate);

        final Estate savedEstate = estateService.save(estate);

        Assertions.assertNotNull(savedEstate);
        Assertions.assertEquals(estate, savedEstate);
        verify(estateRepo).save(estate);
    }

    @Test
    public void updateShouldUpdateEstate() {
        final Estate estate = mock(Estate.class);
        when(estateRepo.save(estate)).thenReturn(estate);

        final Estate updatedEstate = estateService.update(ID, estate);

        Assertions.assertNotNull(updatedEstate);
        Assertions.assertEquals(estate, updatedEstate);
        verify(estateRepo).save(estate);
    }

    @Test
    public void deleteShouldUpdateEstate() {
        estateService.delete(ID);

        verify(estateRepo).deleteById(ID);
    }
}
