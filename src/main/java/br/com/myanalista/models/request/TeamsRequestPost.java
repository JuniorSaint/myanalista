package br.com.myanalista.models.request;

import br.com.myanalista.models.entities.Distributor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TeamsRequestPost {
    private String memberCode;
    @NotNull(message = "Fullname is a mandatory field.")
    private String fullName;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private Integer memberLink;
    private String sellerOrSupervisor;
    @NotNull(message = "Distributor is a mandatory field.")
    private Distributor distributor;
}
