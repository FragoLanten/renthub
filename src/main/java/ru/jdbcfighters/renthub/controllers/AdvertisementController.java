package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.repositories.EstateRepo;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.EstateService;

import java.security.Principal;

@Controller
@RequestMapping("/advertisement")
@RequiredArgsConstructor
@InjectModelAttribute
public class AdvertisementController {

    private final EstateService estateService;

    private final AdvertisementService advertisementService;

    @GetMapping
    public String advertisementList(Model model) {
        Iterable<Estate> estate = estateService.getAll();
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
    @PreAuthorize("hasAuthority('ADMIN, MANAGER')")
    @GetMapping("/administrator")
    public String administratorAdvertisementList(Model model) {
        Iterable<Estate> estate = estateService.getAll();
        model.addAttribute("estates", estate);
        return "administrator_advertisement";
    }

    @PostMapping("/delete/{advertisementId}")
    public String deleteAdvertisement(@PathVariable("advertisementId") Long advertisementId){
        advertisementService.delete(advertisementId);
        return "administrator_advertisement";
    }


}
