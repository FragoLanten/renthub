package ru.jdbcfighters.renthub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.jdbcfighters.renthub.currency.config.CurrencyClientConfig;

@SpringBootApplication
@EnableConfigurationProperties(CurrencyClientConfig.class)
public class RentHubApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentHubApplication.class, args);
    }

}
