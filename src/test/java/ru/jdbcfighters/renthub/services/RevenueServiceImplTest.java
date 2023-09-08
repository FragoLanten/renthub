package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.exception.EstateNotFoundException;
import ru.jdbcfighters.renthub.domain.exception.RevenueByDateNotFoundException;
import ru.jdbcfighters.renthub.domain.exception.RevenueNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;
import ru.jdbcfighters.renthub.services.impl.RevenueServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RevenueServiceImplTest {

    @InjectMocks
    private RevenueServiceImpl revenueService;

    @Mock
    private RevenueRepository revenueRepository;

    @Mock User user;

    @Mock Revenue revenue;

    private static final long ID = 1L;
    private static final LocalDate LOCAL_DATE = LocalDate.of(2023, 7, 17);

    @Test
    public void save_ShouldSaveRevenue() {
        //ARRANGE
        final Revenue revenue = mock(Revenue.class);
        when(revenueRepository.save(revenue)).thenReturn(revenue);
        //ACT
        final Revenue savedRevenue = revenueService.save(revenue);
        //VERIFY
        Assertions.assertNotNull(savedRevenue);
        Assertions.assertEquals(revenue, savedRevenue);
        verify(revenueRepository).save(revenue);
    }

    @Test
    public void getById_ShouldReturnRevenue() {
        //ARRANGE
        final Revenue revenue = mock(Revenue.class);
        when(revenueRepository.findById(ID)).thenReturn(Optional.of(revenue));
        //ACT
        final Revenue actual = revenueService.getById(ID);
        //VERIFY
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(revenue, actual);
        verify(revenueRepository).findById(ID);
    }

    @Test
    public void getById_ifUserNotFound_thenThrow() {
        when(revenueRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(RevenueNotFoundException.class, () ->
                revenueService.getById(ID));
    }

    @Test
    public void delete_ShouldDeleteRevenue() {
        //ACT
        revenueService.delete(ID);
        //VERIFY
        verify(revenueRepository).deleteById(ID);
    }

    @Test
    public void findRevenueByUserId_ShouldReturnListOfRevenue() {
        //ARRANGE
        List<Revenue> revenueList = new ArrayList<>();
        user.setId(ID);
        revenue.setUser(user);
        revenueList.add(revenue);
        revenueList.add(revenue);
        when(revenueRepository.findByUserId(ID)).thenReturn(Optional.of(revenueList));
        //ACT
        List<Revenue> actualList = revenueService.findRevenueByUserId(ID);
        //VERIFY
        Assertions.assertEquals(2, revenueList.size());
        Assertions.assertEquals(revenueList, actualList);
    }

    @Test
    public void findRevenueByUserId_ifUserNotFound_thenThrow() {
        //ARRANGE
        when(revenueRepository.findByUserId(ID)).thenReturn(Optional.empty());
        //VERIFY
        Assertions.assertThrows(RevenueNotFoundException.class, () ->
                revenueService.findRevenueByUserId(ID));
    }

    @Test
    public void findRevenueByDate_ShouldReturnListOfRevenue() {
        //ARRANGE
        List<Revenue> revenueList = new ArrayList<>();
        revenue.setDate(LOCAL_DATE);
        revenueList.add(revenue);
        revenueList.add(revenue);
        when(revenueRepository.findRevenueByDate(LOCAL_DATE)).thenReturn(Optional.of(revenueList));
        //ACT
        List<Revenue> actualList = revenueService.findRevenueByDate(LOCAL_DATE);
        //VERIFY
        Assertions.assertEquals(2, revenueList.size());
        Assertions.assertEquals(revenueList, actualList);
    }

    @Test
    public void findRevenueByDate_ifUserNotFound_thenThrow() {
        //ARRANGE
        when(revenueRepository.findRevenueByDate(LOCAL_DATE)).thenReturn(Optional.empty());
        //VERIFY
        Assertions.assertThrows(RevenueByDateNotFoundException.class, () ->
                revenueService.findRevenueByDate(LOCAL_DATE));
    }

    @Test
    void findAll_ShouldReturnAllRevenues() {
        //ARRANGE
        final List<Revenue> revenueList = new ArrayList<>();
        revenueList.add(new Revenue());
        revenueList.add(new Revenue());
        revenueList.add(new Revenue());
        when(revenueRepository.findAll()).thenReturn(revenueList);
        //ACT
        final List<Revenue> actualList = revenueService.findAll();
        //VERIFY
        Assertions.assertNotNull(revenueList);
        Assertions.assertEquals(revenueList, actualList);
        verify(revenueRepository).findAll();
    }

}