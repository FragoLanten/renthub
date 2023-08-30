package ru.jdbcfighters.renthub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.jdbcfighters.renthub.currency.service.SbrService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SbrService currencyService;

    @GetMapping("/")
    public String greeting(Model model) {
        List<BigDecimal> currency = currencyService.requestByCurrencyCode();
        model.addAttribute("eur", currency.get(0));
        model.addAttribute("usd", currency.get(1));
        return "greeting";
    }

//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Message> messages;
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = messageRepository.findByTag(filter);
//        } else {
//            messages = messageRepository.findAll();
//        }
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//        return "main";
//    }
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
