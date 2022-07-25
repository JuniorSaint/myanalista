package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsSearchResponse extends RepresentationModel<TeamsSearchResponse> {
    private Long id;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
}

