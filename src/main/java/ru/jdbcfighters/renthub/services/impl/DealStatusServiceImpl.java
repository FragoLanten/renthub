package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.exception.DealStatusNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Deal;
import ru.jdbcfighters.renthub.domain.models.DealStatus;
import ru.jdbcfighters.renthub.domain.models.enums.Status;
import ru.jdbcfighters.renthub.repositories.DealStatusRepository;
import ru.jdbcfighters.renthub.services.DealStatusService;

import java.time.LocalDate;

@Service

public class DealStatusServiceImpl implements DealStatusService {

    private final DealStatusRepository dealStatusRepository;

    public DealStatusServiceImpl(DealStatusRepository dealStatusRepository) {
        this.dealStatusRepository = dealStatusRepository;
    }

    @Override
    public void changeStatusToFinishedAndEndDateOfDealById(Deal deal) {
        deal.setDealStatus(findByStatusName(Status.FINISHED));
        deal.setEndDate(LocalDate.now());
    }

    private DealStatus findByStatusName(Status finished) {
        return dealStatusRepository.findByTypeStatus(finished)
                .orElseThrow(() -> new DealStatusNotFoundException(finished));
    }
}
