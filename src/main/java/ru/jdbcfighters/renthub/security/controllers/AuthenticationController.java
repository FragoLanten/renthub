package ru.jdbcfighters.renthub.security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.jdbcfighters.renthub.security.dto.AuthRequest;
import ru.jdbcfighters.renthub.security.dto.AuthResponse;
import ru.jdbcfighters.renthub.security.jwt.JWTConfiguration;
import ru.jdbcfighters.renthub.security.jwt.TokenProvider;
import ru.jdbcfighters.renthub.services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider provider;

    private final UserDetailsService userProvider;

    private final UserService userService;

    private final JWTConfiguration configuration;


    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        /*Check login and password*/
        Authentication authenticate = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getLogin(),
                        request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        /*Generate token with answer to user*/
        return ResponseEntity.ok(AuthResponse.builder().login(request.getLogin()).token(
                provider.generateToken(userProvider.loadUserByUsername(request.getLogin()))).build());
    }

//    @GetMapping("/auth")
//    public void abc () {
//        System.out.println("abc");
//    }

    @DeleteMapping("/logout")
    public void logout(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("Principal object is empty");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && principal.getName().equals(authentication.getName())) {
            SecurityContextHolder.clearContext();
        } else {
            throw new RuntimeException("Security context is clear");
        }
    }

}
