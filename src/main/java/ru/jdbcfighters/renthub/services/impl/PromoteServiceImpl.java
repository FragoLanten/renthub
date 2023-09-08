package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;
import ru.jdbcfighters.renthub.domain.exception.RevenueException;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.repositories.RevenueRepository;
import ru.jdbcfighters.renthub.repositories.UserRepository;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.PromoteService;
import ru.jdbcfighters.renthub.services.UserService;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PromoteServiceImpl implements PromoteService {

    private final AdvertisementService advertisementService;
    private final UserRepository userRepository;
    private final RevenueRepository revenueRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void promoteAdvertisement(Principal principal, AdvertisementDto advertisementDto, Long advertisementId) throws RevenueException{
        User userFromDb = userService.getByLogin(principal.getName());
        Long amountOfDays = Long.valueOf(advertisementDto.amountOfDays());
        Integer rankDto = Integer.valueOf(advertisementDto.rank());
        BigDecimal sumOfPromote = BigDecimal.valueOf((100L * amountOfDays * rankDto));
        int sum = userFromDb.getBalance().compareTo(sumOfPromote);

        if (sum >= 0) {
            int rank = rankDto + advertisementService.get(advertisementId).getRank();
            advertisementService.startPromotion(advertisementService.get(advertisementId), amountOfDays, rank);
            userFromDb.setBalance(userFromDb.getBalance().subtract(sumOfPromote));
            userRepository.save(userFromDb);
            Revenue revenue = new Revenue();
            revenue.setUser(userFromDb);
            revenue.setDate(LocalDate.now());
            revenue.setBalance(sumOfPromote);
            revenueRepository.save(revenue);
        } else throw new RevenueException();
    }
}
