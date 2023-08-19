package ru.jdbcfighters.renthub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.services.RevenueService;

import java.util.List;

@SpringBootApplication
public class RentHubApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RentHubApplication.class, args);
        RevenueService revenueService = context.getBean(RevenueService.class);
        List<Revenue> list = revenueService.findRevenueByUserId(1L);
        Revenue revenue = list.get(0);
        System.out.println(revenue.getSum());
    }

}
