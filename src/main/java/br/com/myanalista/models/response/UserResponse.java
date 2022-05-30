package br.com.myanalista.models.response;

import java.time.LocalDate;
import java.util.Date;

import br.com.myanalista.models.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private Long id;
  private String userName;
  private String userEmail;
  private UserTypeEnum userType;
  private LocalDate createdAt;
  private LocalDate updatedAt;
}
