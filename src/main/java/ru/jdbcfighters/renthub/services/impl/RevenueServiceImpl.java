package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class RevenueServiceImpl {
    @Autowired
    RevenueRepository revenueRepository;

    public Revenue saveRevenue(Revenue revenue) {
        Objects.requireNonNull(revenue);
        return revenueRepository.save(revenue);
    }

    public Revenue getRevenue(Long revenueId) {
        return revenueRepository.findById(revenueId).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено!"));
    }

    public void deleteRevenue(Long revenueID) {
        existCheck(revenueID);
        revenueRepository.deleteById(revenueID);
    }

    public List<Revenue> findRevenueByUserId(Long userID) {
        Objects.requireNonNull(userID);
        return revenueRepository.findRevenueByUserId(userID);
    }

    public List<Revenue> findRevenueByDate(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        return revenueRepository.findRevenueByDate(localDate);
    }

    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }

    private void existCheck(Long revenueId) {
        if (!revenueRepository.existsById(revenueId))
            throw new EntityNotFoundException("Поступление не найдено!");
    }

}
