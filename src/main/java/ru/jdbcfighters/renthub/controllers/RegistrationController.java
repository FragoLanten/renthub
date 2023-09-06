package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.RegistrationUserRequestDto;
import ru.jdbcfighters.renthub.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
@InjectModelAttribute
public class RegistrationController {

    private final UserService userService;

    @GetMapping()
    public String newUser() {
        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") @Valid RegistrationUserRequestDto registrationUserRequestDto,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userService.addUser(registrationUserRequestDto).isEmpty()) {
            model.addAttribute("usernameError", true);
            return "registration";
        }
        return "redirect:/login";
    }
}
