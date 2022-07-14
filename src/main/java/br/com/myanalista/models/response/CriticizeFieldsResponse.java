package br.com.myanalista.models.response;


import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriticizeFieldsResponse {
    private String distributor;
    private String cnpj;
    private List<FieldsCriticizedResponse> criticizes;
}
