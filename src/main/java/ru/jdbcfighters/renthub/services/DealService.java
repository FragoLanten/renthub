package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Deal;
import ru.jdbcfighters.renthub.domain.models.DealStatus;
import ru.jdbcfighters.renthub.domain.models.DealType;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Status;
import ru.jdbcfighters.renthub.domain.models.enums.Type;

import java.time.LocalDate;
import java.util.List;

public interface DealService {

    void createDeal(LocalDate startDate, Estate estate,
                    DealStatus dealStatus, DealType dealType, User buyer);

    Deal saveDeal(Deal deal);

    void changeStatusToFinishedAndEndDateOfDealById(Long id);

    Deal getById(Long id);

    List<Deal> getByDealType(Type dealType);

    List<Deal> getByDealStatus(Status dealStatus);
}
