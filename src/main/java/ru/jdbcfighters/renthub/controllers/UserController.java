package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.domain.dto.UserRequestDto;
import ru.jdbcfighters.renthub.domain.models.User;
import ru.jdbcfighters.renthub.domain.models.enums.Role;
import ru.jdbcfighters.renthub.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@InjectModelAttribute
public class UserController {

    private final UserService userService;

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
//            UserDto userDto,
//            @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user
//    ){
//        userService.save(userDto, user, form);
//        return "redirect:/user";
//    }

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
