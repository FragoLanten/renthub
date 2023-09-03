package ru.jdbcfighters.renthub.currency.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;
import ru.jdbcfighters.renthub.currency.client.HttpCurrencyDateRateClient;
import ru.jdbcfighters.renthub.currency.schema.ValCurs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toMap;

@Service
public class SbrService {

    private final Cache<LocalDate, Map<String, BigDecimal>> cache;

    private final HttpCurrencyDateRateClient client;

    public SbrService(HttpCurrencyDateRateClient client) {
        this.cache = CacheBuilder.newBuilder().build();
        this.client = client;
    }

    public List<BigDecimal> requestByCurrencyCode() {
        try {
            List<BigDecimal> currencyList = new ArrayList<>();
            currencyList.add(cache.get(LocalDate.now(), this::callAllByCurrentDate).get("EUR"));
            currencyList.add(cache.get(LocalDate.now(), this::callAllByCurrentDate).get("USD"));
            return currencyList;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
        String xml = client.requestByDate(LocalDate.now());
        ValCurs response = unmarshall(xml);
        return response.getValute()
                .stream()
                .collect(toMap(ValCurs.Valute::getCharCode, item ->
                        pasreWithLocale(item.getValue())));
    }

    private BigDecimal pasreWithLocale(String currency) {
        try {
            double v = NumberFormat.getNumberInstance(Locale.getDefault())
                    .parse(currency).doubleValue();
            return BigDecimal.valueOf(v).setScale(2, RoundingMode.DOWN);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private ValCurs unmarshall(String xml) {
        try (StringReader reader = new StringReader(xml)) {
            JAXBContext context = JAXBContext.newInstance(ValCurs.class);
            return (ValCurs) context.createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            return new ValCurs();
        }
    }
}
