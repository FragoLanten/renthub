package ru.jdbcfighters.renthub.security.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import ru.jdbcfighters.renthub.security.CustomHeaders;
import ru.jdbcfighters.renthub.security.jwt.TokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;


public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenProvider tokenUtils;

    private final UserDetailsService userDetailsService;

    public AuthenticationTokenFilter(TokenProvider tokenUtils, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenUtils;
        this.userDetailsService = userDetailsService;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {

        Optional.ofNullable((HttpServletRequest) req)
                .map(HttpServletRequest::getCookies)
                .stream()
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals(CustomHeaders.X_AUTH_TOKEN))
                .findFirst()
                .map(Cookie::getValue)
                .filter(tokenUtils::validateToken)
                .map(tokenUtils::getAuthentication)
                .ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));

        filterChain.doFilter(req, res);
    }

}
