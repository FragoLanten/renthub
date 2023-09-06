package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;
import ru.jdbcfighters.renthub.domain.models.Advertisement;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.services.AdvertisementService;
import ru.jdbcfighters.renthub.services.EstateService;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/promote_advertisement")
@RequiredArgsConstructor
@InjectModelAttribute
public class PromoteAdvertisementController {
    private final AdvertisementService advertisementService;
    private final EstateService estateService;
    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/{advertisement}")
    public String promoteAdvertisement(@AuthenticationPrincipal UserDetails userDetails,
                                       @ModelAttribute("advertisement") @Valid AdvertisementDto advertisementDto,
                                       @PathVariable Advertisement advertisement,
                                       Model model){
        model.addAttribute("advertisement", advertisement);
        String userName = userDetails.getUsername();
        Set<Long> availableAdvertisementId = estateService.getAll().stream().filter(estate -> estate.getOwner().getLogin().equals(userName)).map(Estate::getAdvertisement).map(Advertisement::getId).collect(Collectors.toSet());

        Long advertisementId = advertisement.getId();
        if (availableAdvertisementId.contains(advertisementId)) {
            Long amountOfDays = Long.valueOf(advertisementDto.amountOfDays());
            int rank = (-1) * advertisementDto.rank() + advertisementService.get(advertisementId).getRank();
            if (rank > 0) {
                advertisementService.startPromotion(advertisementService.get(advertisementId), amountOfDays, rank);
            }
        }
        return "redirect:/advertisement";
    }
}
