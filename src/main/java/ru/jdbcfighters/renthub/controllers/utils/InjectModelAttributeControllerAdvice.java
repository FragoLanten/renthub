package ru.jdbcfighters.renthub.controllers.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.jdbcfighters.renthub.currency.service.SbrService;

import java.math.BigDecimal;
import java.util.List;

@ControllerAdvice(annotations = InjectModelAttribute.class)
@RequiredArgsConstructor
public class InjectModelAttributeControllerAdvice {

    private final SbrService currencyService;

    @ModelAttribute
    public void addAttributes(Model model) {
        List<BigDecimal> currency = currencyService.requestByCurrencyCode();
        model.addAttribute("eur", currency.get(0));
        model.addAttribute("usd", currency.get(1));
    }

}
