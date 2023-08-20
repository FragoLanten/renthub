package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.services.AdvertisementService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    public Advertisement create(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement get(Long id) {
        return advertisementRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Объявление не найдено!"));
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
        return advertisementRepository.checkIfIdExist(id);
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
