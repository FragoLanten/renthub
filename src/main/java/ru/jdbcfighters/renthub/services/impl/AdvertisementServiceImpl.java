package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.mappers.AdvertisementMapper;
import ru.jdbcfighters.renthub.domain.mappers.EstateMapper;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.repositories.CityRepository;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.repositories.StreetRepository;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementMapper advertisementMapper;
    private EstateMapper estateMapper;
    private AdvertisementRepository advertisementRepository;
    private StreetRepository streetRepository;
    private EstateRepo estateRepo;
    private CityRepository cityRepository;
    private UserService userService;

    @Override
    public Advertisement create(Principal principal, EstateRequestDTO estateRequestDTO) {
        Advertisement advertisement = advertisementMapper.estateRequestDTOToAdvertisement(estateRequestDTO);
        Estate estate = estateMapper.estateRequestDTOToEstate(estateRequestDTO);
        Optional<Street> street = streetRepository.findByName(estateRequestDTO.street());
        Optional<City> city = cityRepository.findByName(estateRequestDTO.city());
        estate.setStreet(street.orElseGet(() -> streetRepository.save(new Street(estateRequestDTO.street()))));
        estate.setCity(city.orElseGet(() -> cityRepository.save(new City(estateRequestDTO.city()))));
        estate.setOwner(userService.getByLogin(principal.getName()));
        Advertisement saveAdvertisement = advertisementRepository.save(advertisement);
        estate.setAdvertisement(saveAdvertisement);
        estateRepo.save(estate);
        return saveAdvertisement;
    }

    @Override
    public Advertisement get(Long id) {
        return advertisementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Объявление не найдено!"));
    }

    @Override
    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    @Override
    public Advertisement update(Advertisement advertisement) {
        existCheck(advertisement.getId());
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement delete(Long id) {
        existCheck(id);
        Advertisement advertisement = get(id);
        advertisement.setVisible(false);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public void hardDelete(Long id) {
        existCheck(id);
        advertisementRepository.deleteById(id);
    }

    @Override
    public boolean checkIfIdExist(Long id) {
        return advertisementRepository.existsById(id);
    }


    @Override
    public Advertisement getAdvertisementByEstate(Estate estate) {
        return advertisementRepository.getAdvertisementByEstate(estate);
    }

    @Override
    public Advertisement rankUp(Advertisement advertisement, Integer rank) {
        advertisement.setRank(advertisement.getRank() + rank);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement rankDown(Advertisement advertisement, Integer rank) {
        advertisement.setRank(advertisement.getRank() - rank);
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement startPromotion(Advertisement advertisement, Long amountOfDays, Integer rank) {
        advertisement.setEndDate(LocalDate.now().plusDays(amountOfDays));
        advertisement.setRank(rank);
        return advertisementRepository.save(advertisement);
    }

    private void existCheck(Long id) {
        if (!advertisementRepository.existsById(id))
            throw new EntityNotFoundException("Объявление не найдено!");
    }


}
