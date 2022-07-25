package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Permission;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoggedResponse extends RepresentationModel<UserLoggedResponse> {
  private String email;
  private String password;
  private List<Permission> permissions;
}
