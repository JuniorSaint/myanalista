package br.com.myanalista.models.response;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriticizeFieldsResponse extends RepresentationModel<CriticizeFieldsResponse> {
    private String distributor;
    private String cnpj;
    private List<FieldsCriticizedResponse> criticizes;
}
