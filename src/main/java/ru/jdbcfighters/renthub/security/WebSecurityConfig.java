package ru.jdbcfighters.renthub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.jdbcfighters.renthub.config.EncoderConfig;
import ru.jdbcfighters.renthub.security.filters.AuthenticationTokenFilter;
import ru.jdbcfighters.renthub.security.jwt.TokenProvider;
import ru.jdbcfighters.renthub.services.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserService userService;

    private final EncoderConfig encoderConfig;

    private final TokenProvider tokenUtils;

    private final UserDetailsService userProvider;

    public WebSecurityConfig(UserService userService, EncoderConfig encoderConfig,
                             TokenProvider tokenUtils, UserDetailsService userProvider) {
        this.userService = userService;
        this.encoderConfig = encoderConfig;
        this.tokenUtils = tokenUtils;
        this.userProvider = userProvider;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoderConfig.getPasswordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationConfiguration authenticationConfiguration() {
        return new AuthenticationConfiguration();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager) {
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtils, userProvider);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers("/registration", "/", "/advertisement", "/static/**", "/auth")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies(CustomHeaders.X_AUTH_TOKEN))
                .addFilterBefore(authenticationTokenFilterBean(authenticationManager(authenticationConfiguration())),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
