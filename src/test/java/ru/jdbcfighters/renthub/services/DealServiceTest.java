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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ru.jdbcfighters.renthub.domain.models.enums.Type.SALE;

@ExtendWith(MockitoExtension.class)
public class DealServiceTest {


    @Mock
    Deal deal;

    Type dealType_type = SALE;

    LocalDate startDate = LocalDate.now();;
    @Mock
    Estate estate ;
    ;
    @Mock
    DealStatus dealStatus;
    @Mock
    DealType dealType;
    @Mock
    User buyer;

    private static final Long DEAL_ID = 1L;
    @Mock
    private DealRepository dealRepository;

    @InjectMocks
    private DealServiceImpl dealService;


    @Test
    void saveTest() {
//        Deal deal = new Deal();
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
    void changeStatusToFinishedAndEndDateOfDealById() {
//        when(createDeal();
//        )
    }
    @Test
    void createDealThrowExceptionTest(){

        Deal deal1 = new Deal();
        deal.setStartDate(startDate);
        deal.setEstate(estate);
        deal.setDealStatus(dealStatus);
        deal.setDealType(dealType);
        deal.setBuyer(buyer);
        dealService.createDeal(deal1.getStartDate(), deal1.getEstate(), deal1.getDealStatus(), deal1.getDealType(), deal1.getBuyer());
        verify(dealService).saveDeal(eq(deal1));

    }
    @Test
    void createDealTest(){

        Deal deal1 = new Deal();
        deal.setStartDate(startDate);
        deal.setEstate(estate);
        deal.setDealStatus(dealStatus);
        deal.setDealType(dealType);
        deal.setBuyer(buyer);
        dealService.createDeal(deal1.getStartDate(), deal1.getEstate(), deal1.getDealStatus(), deal1.getDealType(), deal1.getBuyer());
//        @SuppressWarnings("unchecked")
        verify(dealService).saveDeal(eq(deal1));
        when(dealRepository.save(deal1)).thenReturn(Optional.of(deal1));

    }

}
