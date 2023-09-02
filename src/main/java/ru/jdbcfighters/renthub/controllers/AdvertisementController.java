package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.EstateRepo;

@Controller
@RequestMapping("/advertisement")
@RequiredArgsConstructor
@InjectModelAttribute
public class AdvertisementController {

    private final EstateRepo estateRepo;

    @GetMapping
    public String advertisementList(Model model) {
        Iterable<Estate> estate = estateRepo.findAll();
        model.addAttribute("estates", estate);
        return "advertisement";
    }


}
