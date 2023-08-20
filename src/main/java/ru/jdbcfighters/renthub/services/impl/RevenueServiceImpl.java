package ru.jdbcfighters.renthub.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@Service
@Transactional(readOnly = true)
public class RevenueServiceImpl {
    @Autowired
    RevenueRepository revenueRepository;

    @Transactional
    public Revenue save(Revenue revenue) {
        Objects.requireNonNull(revenue);
        return revenueRepository.save(revenue);
    }

    public Revenue getById(Long revenueId) {
        return revenueRepository.findById(revenueId).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено!"));
    }

    @Transactional
    public void delete(Long revenueID) {
        existCheck(revenueID);
        revenueRepository.deleteById(revenueID);
    }

    public List<Revenue> findRevenueByUserId(Long userID) {
        Objects.requireNonNull(userID);
        return revenueRepository.findByUserId(userID);
    }

    public List<Revenue> findByDate(LocalDate localDate) {
        Objects.requireNonNull(localDate);
        return revenueRepository.findByDate(localDate);
    }

    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }

    private void existCheck(Long revenueId) {
        if (!revenueRepository.existsById(revenueId))
            throw new EntityNotFoundException("Поступление не найдено!");
    }

}
