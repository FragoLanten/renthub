package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.currency.service.SbrService;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.repositories.EstateRepo;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final SbrService currencyService;
    private final EstateRepo estateRepo;

    @ModelAttribute
    public void addAttributes(Model model) {
        List<BigDecimal> currency = currencyService.requestByCurrencyCode();
        model.addAttribute("eur", currency.get(0));
        model.addAttribute("usd", currency.get(1));
    }

    @GetMapping
    public String advertisementList(Model model) {
        Iterable<Estate> estate = estateRepo.findAll();
        model.addAttribute("estates", estate);
        return "advertisement";
    }


}
