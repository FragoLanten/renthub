package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.currency.service.SbrService;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Role;
import ru.jdbcfighters.renthub.services.UserService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SbrService currencyService;

    @ModelAttribute
    public void addAttributes(Model model) {
        List<BigDecimal> currency = currencyService.requestByCurrencyCode();
        model.addAttribute("eur", currency.get(0));
        model.addAttribute("usd", currency.get(1));
    }

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
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @PostMapping
//    public String userSave(
//            @RequestParam String username,
//            @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user
//    ){
//        userService.save(username, user, form);
//        return "redirect:/user";
//    }

//    @GetMapping("/profile")
//    public String getProfile(Model model, @AuthenticationPrincipal User user){
//        model.addAttribute("username", user.getUsername());
//        model.addAttribute("email", user.getEmail());
//        return "profile";
//    }
//
//    @PostMapping("/profile")
//    public String updateProfile(@AuthenticationPrincipal User user,
//                                @RequestParam String password,
//                                @RequestParam String email
//    ){
//      userService.updateProfile(user, password, email);
//    return "redirect:/user/profile";
//    }
}
