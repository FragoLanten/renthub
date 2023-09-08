package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectSbrModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.AdvertisementDto;
import ru.jdbcfighters.renthub.domain.exception.RevenueException;
import ru.jdbcfighters.renthub.services.PromoteService;
import ru.jdbcfighters.renthub.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/promote_advertisement")
@RequiredArgsConstructor
@InjectSbrModelAttribute
public class PromoteAdvertisementController {

    private final PromoteService promoteService;
    private final UserService userService;

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/{advertisementId}")
    public String promoteAdvertisement(Principal principal,
                                       @ModelAttribute("advertisementDto") @Valid AdvertisementDto advertisementDto,
                                       BindingResult bindingResult,
                                       @PathVariable("advertisementId") Long advertisementId,
                                       Model model
                                       )  {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userService.getByLogin(principal.getName()));
            return "profile";
        }
        try{
            promoteService.promoteAdvertisement(principal, advertisementDto, advertisementId);
        }
        catch (RevenueException e){
            model.addAttribute("user", userService.getByLogin(principal.getName()));
            model.addAttribute("exception", e.getMessage());
            return "profile";
        }

        return "redirect:/advertisement";
    }
}