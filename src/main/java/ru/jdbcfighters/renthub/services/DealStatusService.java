package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Deal;

public interface DealStatusService {
    void changeStatusToFinishedAndEndDateOfDealById(Deal deal);
}
