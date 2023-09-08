package ru.jdbcfighters.renthub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectSbrModelAttribute;

@Controller
@InjectSbrModelAttribute
public class MainController {

    @GetMapping("/")
    public String greeting() {
        return "redirect:/advertisement";
    }
}
