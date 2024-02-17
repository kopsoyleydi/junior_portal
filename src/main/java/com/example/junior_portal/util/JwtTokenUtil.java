package com.example.junior_portal.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Component
public class JwtTokenUtil {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Generate a new token.
     **/
    public String generateToken(UserDetails userDetails) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        Map<String, Object> extraClaims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        extraClaims.put("permissions",rolesList);
        return JWT.create()
                .withIssuer("app-personal")
                .withSubject(userDetails.getUsername())
                .withPayload(extraClaims)
                .withExpiresAt(Date.from(generateExpirationDate()))
                .sign(algorithm);
    }


    /**
     * Generate expirationToke.
     **/
    private Instant generateExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }


    /**
     * validate Token.
     **/
    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("app-personal")
                .build()
                .verify(token)
                .getSubject();
    }

    @SneakyThrows
    public String generateTokenToResetPassword(String email, String password) {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();

        tokenService.setServerSecret(password);
        tokenService.setServerInteger(3);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());

        Token token = tokenService.allocateToken(email);

        return token.getKey();
    }
}
