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
        Long amountOfDays = Long.valueOf(advertisementDto.amountOfDays());
        int rank = Math.max((-1) * advertisementDto.rank() + advertisement.getRank(), 1);
        advertisementService.startPromotion(advertisement, amountOfDays, rank);
        return "redirect:/advertisement";
    }
}
