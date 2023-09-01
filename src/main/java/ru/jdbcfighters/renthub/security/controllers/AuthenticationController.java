package ru.jdbcfighters.renthub.security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jdbcfighters.renthub.security.CustomHeaders;
import ru.jdbcfighters.renthub.security.dto.AuthRequest;
import ru.jdbcfighters.renthub.security.dto.AuthResponse;
import ru.jdbcfighters.renthub.security.jwt.JWTConfiguration;
import ru.jdbcfighters.renthub.security.jwt.TokenProvider;
import ru.jdbcfighters.renthub.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider provider;

    private final UserDetailsService userProvider;

    private final UserService userService;

    private final JWTConfiguration configuration;

    @PostMapping("/auth")
    public String login(AuthRequest authRequest,
                        HttpServletResponse res) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        AuthResponse response = AuthResponse.builder().login(authRequest.getLogin())
                .token(provider.generateToken(userProvider.loadUserByUsername(authRequest.getLogin()))).build();
        Cookie cookie = new Cookie(CustomHeaders.X_AUTH_TOKEN, response.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);
        res.addCookie(cookie);

        /*Generate token with answer to user*/
        return "redirect:/";
    }

//    @PostMapping("/auth")
//    public ResponseEntity<AuthResponse> login(@RequestParam String login,
//                                              @RequestParam String password) {
//
//        /*Check login and password*/
//        Authentication authenticate = authenticationManager.authenticate
//                (new UsernamePasswordAuthenticationToken(login, password));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//
//        AuthResponse response = AuthResponse.builder().login(login)
//                .token(provider.generateToken(userProvider.loadUserByUsername(login))).build();
//
//
//        /*Generate token with answer to user*/
//        return ResponseEntity.ok()
//                .header(CustomHeaders.X_AUTH_TOKEN, response.getToken())
//                .body(response);
//    }

    @DeleteMapping("/logout")
    public void logout(Principal principal, HttpServletResponse res) {
        if (principal == null) {
            throw new RuntimeException("Principal object is empty");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Cookie cookie = new Cookie(CustomHeaders.X_AUTH_TOKEN, "");
//        cookie.setPath("/");
//        cookie.setMaxAge(-1);
//        res.addCookie(cookie);
        if (authentication != null && principal.getName().equals(authentication.getName())) {
            SecurityContextHolder.clearContext();
        } else {
            throw new RuntimeException("Security context is clear");
        }
    }

}
