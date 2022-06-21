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
public class TeamsSearchResponse {
    private Long id;
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
}

