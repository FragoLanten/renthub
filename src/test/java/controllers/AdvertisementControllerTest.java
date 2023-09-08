package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.jdbcfighters.renthub.controllers.AdvertisementController;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.EstateService;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {AdvertisementControllerTest.class})
@AutoConfigureMockMvc
public class AdvertisementControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EstateRepo estateRepo;

    @Mock
    private AdvertisementService advertisementService;

    @Mock
    private Principal principal;

    @Mock
    private EstateService estateService;

    @InjectMocks
    private AdvertisementController advertisementController;

    private EstateRequestDTO estateRequestDTO;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        advertisementController = new AdvertisementController(estateService, advertisementService);
        mockMvc = MockMvcBuilders.standaloneSetup(advertisementController).build();
        principal = mock(Principal.class);
        estateRequestDTO = EstateRequestDTO.builder()
                .square(String.valueOf(50.2f))
                .price(String.valueOf(BigDecimal.valueOf(100)))
                .street("Ленинская")
                .city("Волгоград")
                .typeEstate("Дом")
                .balcony("on")
                .flatNumber("1")
                .numberOfFloors("1")
                .floor("1")
                .numberOfRooms("2")
                .balcony("on").build();
    }

    @Test
    @Disabled
    public void testGetAdvertisementList() throws Exception {
        List<Estate> estates = new ArrayList<>();
        when(estateService.getAll()).thenReturn(estates);

        mockMvc.perform(get("/advertisement"))
                .andExpect(status().isOk())
                .andExpect(view().name("advertisement"))
                .andExpect(model().attribute("estates", estates));

        verify(estateService, times(1)).getAll();
        verifyNoMoreInteractions(estateRepo);
    }

    @Test
    @Disabled
    public void testAddAdvertisement() throws Exception {

        mockMvc.perform(post("/advertisement/create")
                        .principal(principal)
                        .flashAttr("advertisementRequestDTO", estateRequestDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/profile"));
    }

    @Test
    public void addAdvertisementToWishList() throws Exception {
        Long advertisementId = 1L;

        mockMvc.perform(post("/advertisement/wishlist/{advertisementId}", advertisementId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("advertisement"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("estates"));
    }

    @Test
    public void deleteAdvertisementFromWishList() throws Exception {
        Long advertisementId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.post("/advertisement/wishlist/delete/{advertisementId}", advertisementId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("profile"));
    }
}

