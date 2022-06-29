package br.com.myanalista.models.request;

import br.com.myanalista.models.entities.Distributor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder

public class ContactRequestPost {

    private String contactDepartament;
    private String contactEmail;
    @NotEmpty(message = "Name is a mandatory field.")
    private String contactName;
    private String contactPhone;
    private Distributor distributor;
}
