package ru.jdbcfighters.renthub.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.jdbcfighters.renthub.services.UserService;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Claims.SUBJECT;
import static java.util.Calendar.MILLISECOND;

@Component

@Slf4j
public class TokenProvider {

    /*Generate JWT Token and fields in token. Also add signature into 3-d part of token*/
    public static final String CREATE_VALUE = "created";

    public static final String ROLES = "roles";

    public static final String JWT = "JWT";

    public static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

    private final JWTConfiguration jwtTokenConfig;

    private final UserService userService;

    public TokenProvider(JWTConfiguration jwtTokenConfig, UserService userService) {
        this.jwtTokenConfig = jwtTokenConfig;
        this.userService = userService;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(getUsernameFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts
                .builder()
                /*Set headers with algo and token type info*/
                .setHeader(generateJWTHeaders())
                /*We create payload with user info, roles, expiration date of token*/
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                /*Signature*/
                .signWith(ALGORITHM, jwtTokenConfig.getSecret())
                .compact();
    }

    private Map<String, Object> generateJWTHeaders() {
        Map<String, Object> jwtHeaders = new LinkedHashMap<>();
        jwtHeaders.put("typ", JWT);
        jwtHeaders.put("alg", ALGORITHM.getValue());
        return jwtHeaders;
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(jwtTokenConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    private Date generateCurrentDate() {
        return new Date();
    }

    private Date generateExpirationDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(MILLISECOND, jwtTokenConfig.getExpiration());
        return calendar.getTime();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(CREATE_VALUE, generateCurrentDate());
        claims.put(ROLES, getEncryptedRoles(userDetails));
        return generateToken(claims);
    }

    private List<String> getEncryptedRoles(UserDetails userDetails) {
        return userDetails.getAuthorities().
                stream()
                .map(GrantedAuthority::getAuthority)
//                .map(s -> s.replace("ROLE_", ""))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = getUsernameFromToken(token);
//        return username.equals(userDetails.getUsername());
//    }
    public boolean validateToken(String token) {
        try {
            if (getClaimsFromToken(token).getExpiration().before(new Date())) {
//                log.info(" JWT token has expired");
                return false;
            }
//            log.info("JWT token is valid");
            return true;
        } catch (JwtException | IllegalArgumentException e) {
//            log.info("Expired or invalid JWT token");
            return false;
        }
    }

}
