package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.repositories.AdvertisementRepository;
import ru.jdbcfighters.renthub.repositories.EstateRepo;

@Controller
@RequestMapping("/advertisement")
@RequiredArgsConstructor
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;

    @GetMapping
    public String advertisementList(Model model){
        Iterable<Advertisement> advertisements = advertisementRepository.findAll();
        model.addAttribute("advertisements", advertisements);
        return "advertisement";
    }


}
