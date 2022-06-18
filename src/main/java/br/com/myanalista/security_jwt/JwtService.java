package br.com.myanalista.security_jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

  @Value("${security.jwt.expiration}")
  private String expiration;
  @Value("${security.jwt.signature-key}")
  private String signatureKey;

  public String generateToken(Users user) {
    Long experationString = Long.valueOf(expiration);
    LocalDateTime dateHourExperation = LocalDateTime.now().plusMinutes(experationString);
    Instant instant = dateHourExperation.atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(instant);

    HashMap<String, Object> claims = new HashMap<>();
    claims.put("email: ", user.getUserEmail());
    claims.put("Administrador", user.isAdministrator());

    return Jwts.builder()
        .setSubject(user.getUserEmail())
        .setExpiration(date)
        .setClaims(claims)
        .signWith(SignatureAlgorithm.HS512, signatureKey)
        .compact();
  }

  private Claims getClaims(String token) throws ExpiredJwtException {
    return Jwts.parser()
        .setSigningKey(signatureKey)
        .parseClaimsJws(token)
        .getBody();
  }

  public String getUserLogged(String token) throws ExpiredJwtException {
    return getClaims(token).getSubject();
  }

  public Boolean validToken(String token) {
    try {
      Claims claims = getClaims(token);
      Date dateExperation = claims.getExpiration();
      LocalDateTime date = dateExperation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      return !LocalDateTime.now().isAfter(date);
    } catch (Exception e) {
      return false;
    }
  }

}
