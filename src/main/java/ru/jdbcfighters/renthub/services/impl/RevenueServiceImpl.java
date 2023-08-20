package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RevenueServiceImpl {
    @Autowired
    RevenueRepository revenueRepository;

    public Revenue saveRevenue(Revenue revenue) {
        return revenueRepository.save(revenue);
    }

    public Revenue getRevenue(Long revenueId) {
        return revenueRepository.getReferenceById(revenueId);
    }

    public void deleteRevenue(Long revenueID) {
        revenueRepository.deleteById(revenueID);
    }

    public List<Revenue> findRevenueByUserId(Long userID) {
        return revenueRepository.findRevenueByUserId(userID);
    }

    public List<Revenue> findRevenueByDate(LocalDate localDate) {
        return revenueRepository.findRevenueByDate(localDate);
    }

    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }

}
