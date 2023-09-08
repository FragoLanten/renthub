package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectSbrModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.PromoteService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/promote_advertisement")
@RequiredArgsConstructor
@InjectSbrModelAttribute
public class PromoteAdvertisementController {

    private final PromoteService promoteService;

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/{advertisementId}")
    public String promoteAdvertisement(Principal principal,
                                       @ModelAttribute("advertisement") @Valid AdvertisementDto advertisementDto,
                                       @PathVariable("advertisementId") Long advertisementId) {
        promoteService.promoteAdvertisement(principal, advertisementDto, advertisementId);
        return "redirect:/advertisement";
    }
}