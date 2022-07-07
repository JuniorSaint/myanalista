package br.com.myanalista.models.response;

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
public class DistributorSearchResponse {
  private String cnpjCpf;
  private String nickName;
  private String customerType;
  private String companyName;
}
