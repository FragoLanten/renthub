package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectSbrModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.UserRequestDto;
import ru.jdbcfighters.renthub.domain.models.Estate;
import ru.jdbcfighters.renthub.domain.models.Revenue;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Role;
import ru.jdbcfighters.renthub.services.RevenueService;
import ru.jdbcfighters.renthub.services.UserService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@InjectSbrModelAttribute
public class UserController {

    private final UserService userService;

    private final RevenueService revenueService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userService.getAll());
        return "userlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "useredit";
    }

    @PostMapping("/edit")
    public String updateUser(@AuthenticationPrincipal UserDetails userDetails,
                             @ModelAttribute("user") @Valid UserRequestDto userRequestDto) {
        String login = userDetails.getUsername();
        userService.updateUser(login, userRequestDto);

        return "redirect:/users/edit";
    }

    @PatchMapping("/{id}")
    public String banned(@PathVariable long id){
        userService.banned(id);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String showProfile(Principal principal, Model model) {

        model.addAttribute("user", userService.getByLogin(principal.getName()));
        return "profile";
    }
    @GetMapping("/profile/admin")
    public String showAdminProfile(Model model) {
        BigDecimal balance = revenueService.getByBalance();
        List<Revenue> revenueListFromDb = revenueService.findAll();

        List<Revenue> revenueList = revenueListFromDb.stream()
                .sorted((r1, r2) -> Long.compare(r2.getId(), r1.getId()))
                .collect(Collectors.toList());
        model.addAttribute("revenueList", revenueList);
        model.addAttribute("balance", balance);
        return "admin_profile";
    }
}
