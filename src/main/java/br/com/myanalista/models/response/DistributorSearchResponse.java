package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistributorSearchResponse extends RepresentationModel<DistributorSearchResponse> {
  private Long id;
  private String cnpjCpf;
  private String nickName;
  private String customerType;
  private String companyName;
}
