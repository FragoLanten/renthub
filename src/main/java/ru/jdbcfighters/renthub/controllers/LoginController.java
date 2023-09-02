package ru.jdbcfighters.renthub.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;


@Controller
@RequiredArgsConstructor
@InjectModelAttribute
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
