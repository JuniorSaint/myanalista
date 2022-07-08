package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse {
  private String email;
  private String password;
  private List<Permission> permissions;
}
