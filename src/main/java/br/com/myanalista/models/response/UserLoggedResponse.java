package br.com.myanalista.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse {
  private String userEmail;
  private String password;
  private String userType;
}
