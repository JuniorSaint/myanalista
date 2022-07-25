package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Permission;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends RepresentationModel<UserResponse> {
  private Long id;
  private String name;
  private String email;
  private Boolean active;
  private LocalDate createdAt;
  private LocalDate updatedAt;
  private List<Permission> permissions;
}
