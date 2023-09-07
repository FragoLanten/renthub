package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;
import ru.jdbcfighters.renthub.services.AdvertisementService;

import javax.validation.Valid;

@Controller
@RequestMapping("/promote_advertisement")
@RequiredArgsConstructor
@InjectModelAttribute
public class PromoteAdvertisementController {
    private final AdvertisementService advertisementService;

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/{advertisementId}")
    public String promoteAdvertisement(@ModelAttribute("advertisement") @Valid AdvertisementDto advertisementDto,
                                       @PathVariable("advertisementId") Long advertisementId){
        Long amountOfDays = advertisementDto.amountOfDays();
        Integer rankDto = advertisementDto.rank();
        if (rankDto != null && amountOfDays != null && rankDto > 0 && amountOfDays > 0) {
            int rank = rankDto + advertisementService.get(advertisementId).getRank();
            advertisementService.startPromotion(advertisementService.get(advertisementId), amountOfDays, rank);
        }
        return "redirect:/advertisement";
    }
}