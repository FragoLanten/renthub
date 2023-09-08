package ru.jdbcfighters.renthub.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectSbrModelAttribute;


@Controller
@RequiredArgsConstructor
@InjectSbrModelAttribute
public class LoginController {

    @GetMapping("/login")
   public String login(){
        return "login";
    }
}
