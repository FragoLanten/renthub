package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.exception.DealNotFoundException;
import ru.jdbcfighters.renthub.domain.exception.DealStatusNotFoundException;
import ru.jdbcfighters.renthub.domain.exception.DealTypeNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Deal;
import ru.jdbcfighters.renthub.domain.models.DealStatus;
import ru.jdbcfighters.renthub.domain.models.DealType;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Status;
import ru.jdbcfighters.renthub.domain.models.enums.Type;
import ru.jdbcfighters.renthub.repositories.DealRepository;
import ru.jdbcfighters.renthub.services.DealService;
import ru.jdbcfighters.renthub.services.DealStatusService;

import java.time.LocalDate;
import java.util.List;

@Service

public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final DealStatusService dealStatusService;

    public DealServiceImpl(DealRepository dealRepository, DealStatusService dealStatusService) {
        this.dealRepository = dealRepository;
        this.dealStatusService = dealStatusService;
    }

    @Override
    public void createDeal(LocalDate startDate, Estate estate,
                           DealStatus dealStatus, DealType dealType,
                           User buyer) throws IllegalArgumentException {
        if (startDate == null || estate == null || dealStatus == null
                || dealType == null || buyer == null) {
            throw new IllegalArgumentException("Переданы не верные аргументы");
        }
        Deal deal = new Deal();
        deal.setStartDate(startDate);
        deal.setEstate(estate);
        deal.setDealStatus(dealStatus);
        deal.setDealType(dealType);
        deal.setBuyer(buyer);
        saveDeal(deal);
    }

    @Override
    public Deal saveDeal(Deal deal) {
        return dealRepository.save(deal);
    }

    @Override
    public void changeStatusToFinishedAndEndDateOfDealById(Long id) {
        Deal deal = getById(id);
        dealStatusService.changeStatusToFinishedAndEndDateOfDealById(deal);
        dealRepository.save(deal);

    }

    @Override
    public Deal getById(Long id) {
        return dealRepository.findById(id).orElseThrow(
                () -> new DealNotFoundException(id)
        );
    }

    @Override
    public List<Deal> getByDealType(Type dealType) {
        return dealRepository.getDealsByDealType_Type(dealType).orElseThrow(
                () -> new DealTypeNotFoundException(dealType)
        );
    }

    @Override
    public List<Deal> getByDealStatus(Status dealStatus) {
        return dealRepository.getDealsByDealStatus_Status(dealStatus).orElseThrow(
                () -> new DealStatusNotFoundException(dealStatus)
        );
    }
}
