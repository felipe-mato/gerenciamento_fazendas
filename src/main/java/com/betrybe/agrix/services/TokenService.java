package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Class.
 */
@Service
public class TokenService {

  @Value("${api.security.token.secret}")
  private String secret;

  /**
   * Gera um Token.
   */
  public String generateToken(UserDetails userDetails) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("trybe")
        .withSubject(userDetails.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Gera data de expiração para "Token".
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

  /**
   * Valida Token.
   */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
        .withIssuer("trybe")
        .build()
        .verify(token)
        .getSubject();
  }
}