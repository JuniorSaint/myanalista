package br.com.myanalista.models.response;

import java.time.LocalDate;
import java.util.List;

import br.com.myanalista.models.entities.Permission;
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
public class UserResponse {
  private Long id;
  private String userName;
  private String email;
  private List<Permission> permissions;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
