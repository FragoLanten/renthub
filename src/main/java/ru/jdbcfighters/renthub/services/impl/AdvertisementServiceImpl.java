package ru.jdbcfighters.renthub.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Attr;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.mappers.AdvertisementMapper;
import ru.jdbcfighters.renthub.domain.mappers.EstateMapper;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Attribute;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.repositories.AttributeRepository;
import ru.jdbcfighters.renthub.repositories.AttributeValueRepository;
import ru.jdbcfighters.renthub.repositories.CityRepository;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.repositories.StreetRepository;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.UserService;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private AdvertisementMapper advertisementMapper;
    private EstateMapper estateMapper;
    private AdvertisementRepository advertisementRepository;
    private StreetRepository streetRepository;
    private AttributeValueRepository attributeValueRepository;
    private AttributeRepository attributeRepository;
    private EstateRepo estateRepo;
    private CityRepository cityRepository;
    private UserService userService;
    private List<Attribute> finalAttributeList;
    private List<AttributeValue> finalAttributeValueList;

    public AdvertisementServiceImpl() {
    }

    public AdvertisementServiceImpl(AdvertisementMapper advertisementMapper, EstateMapper estateMapper, AdvertisementRepository advertisementRepository, StreetRepository streetRepository, AttributeValueRepository attributeValueRepository, AttributeRepository attributeRepository, EstateRepo estateRepo, CityRepository cityRepository, UserService userService, List<Attribute> finalAttributeList, List<AttributeValue> finalAttributeValueList) {
        this.advertisementMapper = advertisementMapper;
        this.estateMapper = estateMapper;
        this.advertisementRepository = advertisementRepository;
        this.streetRepository = streetRepository;
        this.attributeValueRepository = attributeValueRepository;
        this.attributeRepository = attributeRepository;
        this.estateRepo = estateRepo;
        this.cityRepository = cityRepository;
        this.userService = userService;
        this.finalAttributeList = finalAttributeList;
        this.finalAttributeValueList = finalAttributeValueList;
    }

    @Override
    public Advertisement create(Principal principal, EstateRequestDTO estateRequestDTO) {
        setAttributeValue(estateRequestDTO);
        Advertisement advertisement = advertisementMapper.estateRequestDTOToAdvertisement(estateRequestDTO);
        Estate estate = estateMapper.estateRequestDTOToEstate(estateRequestDTO);
        Optional<Street> street = streetRepository.findByName(estateRequestDTO.street());
        Optional<City> city = cityRepository.findByName(estateRequestDTO.city());
        estate.setStreet(street.orElseGet(() -> streetRepository.save(new Street(estateRequestDTO.street()))));
        estate.setCity(city.orElseGet(() -> cityRepository.save(new City(estateRequestDTO.city()))));
        estate.setOwner(userService.getByLogin(principal.getName()));
        Advertisement saveAdvertisement = advertisementRepository.save(advertisement);
        estate.setAdvertisement(saveAdvertisement);
        estate.setAttributeValue(finalAttributeValueList);
        estate.setAttributes(finalAttributeList);
        estateRepo.save(estate);

        return saveAdvertisement;
    }

    private void setAttributeValue(EstateRequestDTO estateRequestDTO) {
        List<Attribute> attributeList = attributeRepository.findAll();
     finalAttributeList = new ArrayList<>();
     finalAttributeValueList = new ArrayList<>();
        List<String> lists = new ArrayList<>();
        Map<Long, String> mapAttribute = attributeList.stream()
                .collect(Collectors.toMap((Attribute::getId), (Attribute::getName)));
        lists.add(estateRequestDTO.flatNumber());
        lists.add(estateRequestDTO.numberOfFloors());
        lists.add(estateRequestDTO.floor());
        lists.add(estateRequestDTO.numberOfRooms());
        lists.add(estateRequestDTO.balcony());
        lists.add(estateRequestDTO.typeEstate());

        long index = 1L;

        for (String list : lists) {
            if (list != null) {
                AttributeValue av = new AttributeValue();
                Attribute a = new Attribute();
                av.setName(list);
                a.setName(mapAttribute.get(index));
                a.setId(index);
                attributeValueRepository.save(av);
                finalAttributeList.add(a);
                finalAttributeValueList.add(av);
            }
            index++;
        }
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
