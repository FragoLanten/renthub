package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.EstateRequestDTO;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.EstateService;

import javax.validation.Valid;
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
    public String addAdvertisement(Principal principal,
                                   @ModelAttribute("estate") @Valid EstateRequestDTO estateRequestDTO,
                                   BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "add_advertisement";
        }
        advertisementService.create(principal, estateRequestDTO);
        return "redirect:/user/profile";
    }
    @PreAuthorize("hasAuthority('ADMIN, MANAGER')")
    @GetMapping("/administrator")
    public String administratorAdvertisementList(Model model) {
        Iterable<Estate> estate = estateService.getAll();
        model.addAttribute("estates", estate);
        return "administrator_advertisement";
    }
    @PreAuthorize("hasAuthority('ADMIN, MANAGER')")
    @PostMapping("/admin/delete/{advertisementId}")
    public String adminDeleteAdvertisement(@PathVariable("advertisementId") Long advertisementId){
        advertisementService.delete(advertisementId);
        return "redirect:/advertisement/administrator";
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/seller/delete/{advertisementId}")
    public String sellerDeleteAdvertisement(@PathVariable("advertisementId") Long advertisementId){
        advertisementService.delete(advertisementId);
        return "redirect:/user/profile";
    }

}
