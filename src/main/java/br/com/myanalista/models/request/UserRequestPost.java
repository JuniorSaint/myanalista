package br.com.myanalista.models.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.myanalista.models.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserRequestPost {
  @NotEmpty(message = "Field 'userName' can't be empty")
  private String userName;

  @NotEmpty(message = "Field 'userEmail' can't be empty")
  @Email(message = "Format field 'userEmail' is wrong")
  @Column(unique=true)
  private String userEmail;

  @NotEmpty(message = "Field 'password' can't be empty")
  private String password;

  @JsonDeserialize
  private UserTypeEnum userType;
}
