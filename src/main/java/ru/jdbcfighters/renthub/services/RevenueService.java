package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.models.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface RevenueService {
    public Revenue save(Revenue revenue);

    public Revenue getById(Long revenueId);

    public void delete(Long revenueID);

    public List<Revenue> findRevenueByUserId(Long userID);

    public List<Revenue> findRevenueByDate(LocalDate localDate);

    public List<Revenue> findAll();
}
