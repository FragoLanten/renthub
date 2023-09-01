package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import ru.jdbcfighters.renthub.controllers.utils.InjectModelAttribute;
import ru.jdbcfighters.renthub.security.CustomHeaders;
import ru.jdbcfighters.renthub.security.jwt.TokenProvider;

@Controller
@RequiredArgsConstructor
@InjectModelAttribute
public class MainController {

    private final TokenProvider tokenProvider;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@CookieValue(value = CustomHeaders.X_AUTH_TOKEN, defaultValue = "null") String jwtToken,  Model model) {
       if (tokenProvider.validateToken(jwtToken)){
         return "main";
       }else
        return "login";
    }
//
//    @PostMapping("/main")
//    public String add(
//            @Valid Message message,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file,
//            @AuthenticationPrincipal User user) {
//        message.setAuthor(user);
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("message", message);
//            return "/main";
//        } else {
//            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
//                File uploadDir = new File(uploadPath);
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//                String uuidFile = UUID.randomUUID().toString();
//                String resultFileName = uuidFile + "." + file.getOriginalFilename();
//
//                try {
//                    file.transferTo(new File(uploadPath + "/" + resultFileName));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                message.setFilename(resultFileName);
//            }
//            model.addAttribute("message", null);
//            messageRepository.save(message);
//        }
//        Iterable<Message> messages = messageRepository.findAll();
//        model.addAttribute("messages", messages);
//        return "main";
//    }
}
