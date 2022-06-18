package br.com.myanalista.models.response;

import java.time.LocalDate;

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
public class UserResponse {
  private Long id;
  private String userName;
  private String userEmail;
  private boolean administrator;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
