package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.services.AdvertisementService;

import java.security.Principal;

@Controller
@RequestMapping("/advertisement")
@RequiredArgsConstructor
@InjectModelAttribute
public class AdvertisementController {

    private final EstateRepo estateRepo;

    private final AdvertisementService advertisementService;

    @GetMapping
    public String advertisementList(Model model) {
        Iterable<Estate> estate = estateRepo.findAll();
        model.addAttribute("estates", estate);
        return "advertisement";
    }
    @GetMapping("/create")
    public String show(){
        return "add_advertisement";
    }

    @PostMapping("/create")
    public String addAdvertisement(Principal principal, EstateRequestDTO advertisementRequestDTO){
        advertisementService.create(principal, advertisementRequestDTO);
        return "redirect:/user/profile";
    }


}
