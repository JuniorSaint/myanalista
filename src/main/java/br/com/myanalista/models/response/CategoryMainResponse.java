package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class CategoryMainResponse extends RepresentationModel<CategoryMainResponse> {
    private Long id;
    private String name;
}

