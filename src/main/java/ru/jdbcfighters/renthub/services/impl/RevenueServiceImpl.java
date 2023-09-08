package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jdbcfighters.renthub.domain.exception.RevenueByDateNotFoundException;
import ru.jdbcfighters.renthub.domain.exception.RevenueNotFoundException;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;
import ru.jdbcfighters.renthub.services.RevenueService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RevenueServiceImpl implements RevenueService {

    private final RevenueRepository revenueRepository;

    @Transactional
    public Revenue save(Revenue revenue) {
        Objects.requireNonNull(revenue);
        return revenueRepository.save(revenue);
    }

    public Revenue getById(Long revenueId) {
        return revenueRepository.findById(revenueId).orElseThrow(() -> new RevenueNotFoundException(revenueId));
    }

    @Transactional
    public void delete(Long revenueID) {
        revenueRepository.deleteById(revenueID);
    }

    public List<Revenue> findRevenueByUserId(Long userID) {
        Optional<List<Revenue>> optList = revenueRepository.findByUserId(userID);
        if (optList.isPresent()){
            return optList.get();
        }
        throw new RevenueNotFoundException(userID);
    }

    public List<Revenue> findRevenueByDate(LocalDate localDate) {
        Optional<List<Revenue>> optList = revenueRepository.findRevenueByDate(localDate);
        if (optList.isPresent()){
            return optList.get();
        }
        throw new RevenueByDateNotFoundException(localDate);
    }

    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }

    @Override
    public BigDecimal getByBalance() {
        return revenueRepository.getBalance();
    }
}
