package ru.jdbcfighters.renthub.currency.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import ru.jdbcfighters.renthub.currency.client.CbrCurrencyRateClient;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SbrServiceTest {

    private SbrService sbrService;
    @Value("${currency.client.url-test}")
    private String url;
    private final CbrCurrencyRateClient client = new CbrCurrencyRateClient();
    private Field urlField;

    private Method parseWithLocaleMethod;

    @BeforeEach
    void init() throws NoSuchFieldException{
        urlField = client.getClass().getDeclaredField("url");
        urlField.setAccessible(true);
        sbrService = new SbrService(client);
    }

    @Test
    void getCurrencyList_IfSuccess_ThanReturnList() throws IllegalAccessException {
        urlField.set(client, "http://www.cbr.ru/scripts/XML_daily.asp");
        List<BigDecimal> expectedList = sbrService.requestByCurrencyCode();
        assertAll(
                () -> assertThat(expectedList).isNotNull(),
                () -> assertThat(expectedList).hasSize(2)
        );
    }
    @Test
    void throwException_IfUrl_IsNull() throws IllegalAccessException {
        urlField.set(client, "");
        Assertions.assertThrows(RuntimeException.class, () -> sbrService.requestByCurrencyCode());

    }
//    @Test
//    void throwException_IfCurrency_IsNull() throws NoSuchMethodException, IllegalAccessException {
//        urlField.set(client, "");
//        parseWithLocaleMethod = sbrService.getClass().getDeclaredMethod("parseWithLocale", String.class);
//        parseWithLocaleMethod.setAccessible(true);
//        parseWithLocaleMethod("");
//        Assertions.assertThrows(ParseException.class, () -> sbrService.requestByCurrencyCode());
   // }

}