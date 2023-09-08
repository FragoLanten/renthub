package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.models.*;
import ru.jdbcfighters.renthub.domain.models.enums.Type;
import ru.jdbcfighters.renthub.repositories.DealRepository;
import ru.jdbcfighters.renthub.services.impl.DealServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static ru.jdbcfighters.renthub.domain.models.enums.Status.PROCESSING;
import static ru.jdbcfighters.renthub.domain.models.enums.Type.RENT;
import static ru.jdbcfighters.renthub.domain.models.enums.Type.SALE;

@ExtendWith(MockitoExtension.class)
public class DealServiceTest {


    Deal deal;

    Type dealType_type = SALE;

    private static final Long DEAL_ID = 1L;
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;


    @Test
    void saveTest() {
        dealService.saveDeal(deal);
        verify(dealRepository).save(eq(deal));
    }

    @Test
    void getByIdTest() {
        Deal deal1 = new Deal();
        when(dealRepository.findById(DEAL_ID)).thenReturn(Optional.of(deal1));
        Deal deal2 = dealService.getById(DEAL_ID);
        assertEquals(deal1, deal2);

    }

    @Test
    void getByDealTypeTest() {
        List<Deal> dealList = new ArrayList<>();
        when(dealRepository.getDealsByDealType_Type(dealType_type)).thenReturn(Optional.of(dealList));
        List<Deal> actualresulList = dealService.getByDealType(dealType_type);
        assertEquals(dealList, actualresulList);
    }

    @Test
    void getByDealStatusTest() {
        List<Deal> dealList = new ArrayList<>();
        when(dealRepository.getDealsByDealType_Type(dealType_type)).thenReturn(Optional.of(dealList));
        List<Deal> actualresulList = dealService.getByDealType(dealType_type);
        assertEquals(dealList, actualresulList);
    }

    @Test
    void createDealTest() {
        Set<Deal> dealSet = new HashSet<>();
        LocalDate startDate = LocalDate.now();
        DealStatus dealStatus = new DealStatus(1L, PROCESSING, dealSet);
        User buyer = new User();
        Estate estate;
        Street street;
        City city;
        street = Street.builder()
                .name("Ленинская")
                .build();
        city = City.builder()
                .name("Волгоград")
                .build();
        estate = Estate.builder()
                .number(1)
                .square(50.2f)
                .price(BigDecimal.valueOf(100))
                .street(street)
                .city(city)
                .build();
        DealType dealType = new DealType(1L, RENT, dealSet);

        Deal deal1 = new Deal();
        buyer.setId(1L);
        dealService.createDeal(startDate, estate, dealStatus, dealType, buyer);
        deal1.setStartDate(startDate);
        deal1.setEstate(estate);
        deal1.setDealStatus(dealStatus);
        deal1.setDealType(dealType);
        deal1.setBuyer(buyer);
        verify(dealRepository).save(any(Deal.class));
        assertThat(startDate).isEqualTo(deal1.getStartDate());
        assertThat(estate).isEqualTo(deal1.getEstate());
        assertThat(dealStatus).isEqualTo(deal1.getDealStatus());
        assertThat(dealType).isEqualTo(deal1.getDealType());
        assertThat(buyer).isEqualTo(deal1.getBuyer());
    }
}
