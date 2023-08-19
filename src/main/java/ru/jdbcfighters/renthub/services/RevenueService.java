package ru.jdbcfighters.renthub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RevenueService {
    @Autowired
    RevenueRepository revenueRepository;

    @Transactional
    public List<Revenue> findRevenueByUserId(Long userID) {
        return revenueRepository.findRevenueByUserId(userID);
    }

    @Transactional
    public List<Revenue> findAll() {
        return revenueRepository.findAll();
    }
}
