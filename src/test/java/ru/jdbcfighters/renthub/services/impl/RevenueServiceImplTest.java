package ru.jdbcfighters.renthub.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Role;
import ru.jdbcfighters.renthub.services.RevenueService;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootTest
class RevenueServiceImplTest {

    @Autowired
    private RevenueServiceImpl revenueService;

    @Autowired
    private UserServiceImpl userService;

    protected static BigDecimal BD = new BigDecimal("5000");
    protected static final User user1 = new User(31L, "Oleg", "Danilov", "oleg", "danilov", "123", false, BD, null, null,null, Role.BUYER);
//    protected static final User user2 = new User();
    protected static final Revenue revenue1 = new Revenue(19L, user1, LocalDate.now(), BD);


    @BeforeEach
    public void setUp() {

    }


    @Test
    void save() {
        userService.create(user1);
        revenueService.save(revenue1);
        assertGet(revenue1);
    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findRevenueByUserId() {
    }

    @Test
    void findByDate() {
    }

    @Test
    void findAll() {
    }

    public void assertGet(Revenue revenue) {
        Assertions.assertEquals(revenue, revenueService.getById(revenue.getId()));
    }
}