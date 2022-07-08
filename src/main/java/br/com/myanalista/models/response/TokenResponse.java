package br.com.myanalista.models.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponse {
  private String username;
  private Boolean authenticated;
  private Date created;
  private Date expiration;
  private String accessToken;
  private String refreshToken;
}
