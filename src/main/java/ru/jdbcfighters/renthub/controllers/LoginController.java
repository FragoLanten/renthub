package ru.jdbcfighters.renthub.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jdbcfighters.renthub.currency.service.SbrService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final SbrService currencyService;

    @GetMapping("/login")
    public String login(Model model) {
        List<BigDecimal> currency = currencyService.requestByCurrencyCode();
        model.addAttribute("eur", currency.get(0));
        model.addAttribute("usd", currency.get(1));
        return "login";
    }
}
