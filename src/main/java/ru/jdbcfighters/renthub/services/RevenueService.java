package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Revenue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RevenueService {
    Revenue save(Revenue revenue);

    Revenue getById(Long revenueId);

    void delete(Long revenueID);

    List<Revenue> findRevenueByUserId(Long userID);

    List<Revenue> findRevenueByDate(LocalDate localDate);

    List<Revenue> findAll();

    BigDecimal getByBalance();
}
