package br.com.myanalista.models.response;

import br.com.myanalista.models.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse {
  private String userEmail;
  private String password;
  private UserTypeEnum userType;
}
