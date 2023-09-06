package ru.jdbcfighters.renthub.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.mappers.AdvertisementMapper;
import ru.jdbcfighters.renthub.domain.mappers.EstateMapper;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Attribute;
import ru.jdbcfighters.renthub.domain.models.AttributeValue;
import ru.jdbcfighters.renthub.domain.models.City;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.Street;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.repositories.AttributeRepository;
import ru.jdbcfighters.renthub.repositories.AttributeValueRepository;
import ru.jdbcfighters.renthub.repositories.CityRepository;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.repositories.StreetRepository;
import ru.jdbcfighters.renthub.services.impl.AdvertisementServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AdvertisementServiceImplTest {

    @Mock
    private AdvertisementMapper advertisementMapper;

    @Mock
    private EstateMapper estateMapper;

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private StreetRepository streetRepository;

    @Mock
    private EstateRepo estateRepo;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private UserService userService;

    @Mock
    private AttributeRepository attributeRepository;

    @Mock
    private AttributeValueRepository attributeValueRepository;

    @InjectMocks
    private AdvertisementServiceImpl advertisementService;

    private Principal principal;

    private EstateRequestDTO estateRequestDTO;

    private Advertisement advertisement;

    private Estate estate;

    private Street street;

    private City city;

    @BeforeEach
    void setup() {
        estateRequestDTO = EstateRequestDTO.builder()
                .square(50.2f)
                .price(BigDecimal.valueOf(100))
                .street("Ленинская")
                .city("Волгоград")
                .typeEstate("Дом")
                .balcony("on")
                .flatNumber("1")
                .numberOfFloors("1")
                .floor("1")
                .numberOfRooms("2")
                .balcony("on").build();
        principal = mock(Principal.class);
        street = Street.builder()
                .name("Ленинская")
                .build();
        city = City.builder()
                .name("Волгоград")
                .build();
        estate = Estate.builder()
                .number(1)
                .square(50.2f)
                .price(BigDecimal.valueOf(100))
                .street(street)
                .city(city)
                .build();
        advertisement = Advertisement.builder()
                .endDate(LocalDate.now().plusDays(2))
                .visible(true)
                .rank(1)
                .moderated(false)
                .estate(estate)
                .build();
    }

    @Test
    void create_Success() {
        List<Attribute> attributes = new ArrayList<>();
        Attribute attribute1 = new Attribute();
        Attribute attribute2 = new Attribute();
        attribute1.setName("Тип");
        attribute1.setId(1L);
        attribute2.setName("Обзор");
        attribute2.setId(2L);
        attributes.add(attribute1);
        attributes.add(attribute2);
        when(principal.getName()).thenReturn("username");
        when(advertisementMapper.estateRequestDTOToAdvertisement(estateRequestDTO)).thenReturn(advertisement);
        when(estateMapper.estateRequestDTOToEstate(estateRequestDTO)).thenReturn(estate);
        when(streetRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(cityRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(streetRepository.save(any(Street.class))).thenReturn(street);
        when(cityRepository.save(any(City.class))).thenReturn(city);
        when(userService.getByLogin(anyString())).thenReturn(new User());
        when(attributeRepository.findAll()).thenReturn(attributes);
        when(attributeValueRepository.save(any())).thenReturn(new AttributeValue());
        when(advertisementRepository.save(any(Advertisement.class))).thenReturn(advertisement);

        Advertisement result = advertisementService.create(principal, estateRequestDTO);

        verify(advertisementMapper, times(1)).estateRequestDTOToAdvertisement(estateRequestDTO);
        verify(estateMapper, times(1)).estateRequestDTOToEstate(estateRequestDTO);
        verify(streetRepository, times(1)).findByName(anyString());
        verify(cityRepository, times(1)).findByName(anyString());
        verify(streetRepository, times(1)).save(any(Street.class));
        verify(cityRepository, times(1)).save(any(City.class));
        verify(userService, times(1)).getByLogin(anyString());
        verify(advertisementRepository, times(1)).save(any(Advertisement.class));
        verify(estateRepo, times(1)).save(any(Estate.class));
        assertNotNull(result);
        assertEquals(advertisement, result);
    }

    @Test
    void get_Success() {
        when(advertisementRepository.findById(anyLong())).thenReturn(Optional.ofNullable(advertisement));

        Advertisement newAdvertisement = advertisementService.get(1L);

        verify(advertisementRepository, times(1)).findById(any());
        assertEquals(advertisement, newAdvertisement);

    }

    @Test
    void get_Exception() {
        when(advertisementRepository.findById(anyLong())).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> advertisementService.get(523123L));

    }

    @Test
    void getAll_Success() {
        List<Advertisement> advertisements = new ArrayList<>();
        advertisements.add(advertisement);
        advertisements.add(advertisement);
        when(advertisementRepository.findAll()).thenReturn(advertisements);
        List<Advertisement> newAdvertisements = advertisementService.getAll();
        assertEquals(newAdvertisements, advertisements);
    }

    @Test
    void update_Success() {
        Advertisement advertisementToUpdate = advertisement;
        advertisementToUpdate.setId(20L);
        advertisementToUpdate.setRank(3);

        when(advertisementRepository.existsById(anyLong())).thenReturn(true);
        when(advertisementRepository.save(any())).thenReturn(advertisementToUpdate);

        Advertisement update = advertisementService.update(advertisementToUpdate);

        advertisement.setRank(3);

        assertEquals(advertisement, update);

    }

    @Test
    void delete_Success() {
        advertisement.setId(1L);

        when(advertisementRepository.existsById(any())).thenReturn(true);
        when(advertisementRepository.findById(any())).thenReturn(Optional.ofNullable(advertisement));
        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);

        Advertisement result = advertisementService.delete(advertisement.getId());

        verify(advertisementRepository).existsById(advertisement.getId());
        verify(advertisementRepository).save(advertisement);

        assertFalse(result.getVisible());
    }

    @Test
    void hardDelete_Success() {
        Long advertisementId = 1L;

        when(advertisementRepository.existsById(advertisementId)).thenReturn(true);

        advertisementService.hardDelete(advertisementId);

        verify(advertisementRepository).existsById(advertisementId);
        verify(advertisementRepository).deleteById(advertisementId);
    }

    @Test
    void hardDelete_Exception() {
        Long advertisementId = 1L;

        when(advertisementRepository.existsById(advertisementId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> advertisementService.hardDelete(advertisementId));

    }

    @Test
    void rankUp_Success() {
        advertisement.setId(1L);
        advertisement.setRank(5);

        Integer rankIncrease = 3;
        Integer expectedRank = advertisement.getRank() + rankIncrease;

        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);

        Advertisement result = advertisementService.rankUp(advertisement, rankIncrease);

        verify(advertisementRepository).save(advertisement);

        assertEquals(expectedRank, result.getRank());
    }

    @Test
    void rankDown_Success() {
        advertisement.setId(1L);
        advertisement.setRank(5);

        Integer rankDecrease = 2;
        Integer expectedRank = advertisement.getRank() - rankDecrease;

        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);

        Advertisement result = advertisementService.rankDown(advertisement, rankDecrease);

        verify(advertisementRepository).save(advertisement);

        assertEquals(expectedRank, result.getRank());
    }

    @Test
    void startPromotion_Success() {
        advertisement.setId(1L);
        advertisement.setRank(5);

        long amountOfDays = 7L;
        Integer rank = 10;

        LocalDate expectedEndDate = advertisement.getEndDate().plusDays(amountOfDays);

        when(advertisementRepository.save(advertisement)).thenReturn(advertisement);

        Advertisement result = advertisementService.startPromotion(advertisement, amountOfDays, rank);

        verify(advertisementRepository).save(advertisement);

        assertEquals(expectedEndDate, result.getEndDate());
        assertEquals(rank, result.getRank());
    }

    @Test
    void checkIfIdExist_Existing() {
        Long existingId = 1L;

        when(advertisementRepository.existsById(existingId)).thenReturn(true);

        boolean result = advertisementService.checkIfIdExist(existingId);

        assertTrue(result);
    }

    @Test
    void checkIfIdExist_NonExisting() {
        Long nonExistingId = 2L;

        when(advertisementRepository.existsById(nonExistingId)).thenReturn(false);

        boolean result = advertisementService.checkIfIdExist(nonExistingId);

        assertFalse(result);
    }

    @Test
    void getAdvertisementByEstate_Existing() {

        when(advertisementRepository.getAdvertisementByEstate(estate)).thenReturn(advertisement);

        Advertisement result = advertisementService.getAdvertisementByEstate(estate);

        assertEquals(advertisement, result);
    }

    @Test
    void getAdvertisementByEstate_NonExisting() {

        when(advertisementRepository.getAdvertisementByEstate(estate)).thenReturn(null);

        Advertisement result = advertisementService.getAdvertisementByEstate(estate);

        assertNull(result);
    }
}
