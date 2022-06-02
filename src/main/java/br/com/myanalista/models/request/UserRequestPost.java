package br.com.myanalista.models.request;

import javax.persistence.Column;
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

  private String userName;
  @Column(unique=true)
  private String userEmail;
  private String password;
  @JsonDeserialize
  private UserTypeEnum userType;
}
