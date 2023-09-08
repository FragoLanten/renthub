package ru.jdbcfighters.renthub.services;

import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;

import java.security.Principal;

public interface PromoteService {

    void promoteAdvertisement(Principal principal, AdvertisementDto advertisementDto, Long advertisementId);
}
