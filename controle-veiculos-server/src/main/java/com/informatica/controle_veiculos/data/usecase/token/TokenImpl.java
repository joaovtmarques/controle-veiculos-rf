package com.informatica.controle_veiculos.data.usecase.token;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.informatica.controle_veiculos.data.dto.auth.AuthResponseDTO;
import com.informatica.controle_veiculos.data.exception.UnauthorizedException;
import com.informatica.controle_veiculos.domain.model.Admin;
import com.informatica.controle_veiculos.domain.usecase.token.TokenUseCase;

@Service
public class TokenImpl implements TokenUseCase {
  @Value("${jwt.private.key}")
  private String secret;

  public AuthResponseDTO generateToken(Admin admin) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT.create()
          .withIssuer("controleveiculos")
          .withSubject(admin.getEmail())
          .withExpiresAt(this.generateExpirationDate())
          .sign(algorithm);
      return new AuthResponseDTO(token, admin);
    } catch (JWTCreationException exception) {
      throw new UnauthorizedException("Erro ao tentar autenticar");
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("controleveiculos")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException exception) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }
}
