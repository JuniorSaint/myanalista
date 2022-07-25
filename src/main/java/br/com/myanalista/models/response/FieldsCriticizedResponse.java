package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldsCriticizedResponse extends RepresentationModel<FieldsCriticizedResponse> {
    private String field;
    private String customerName;
}
