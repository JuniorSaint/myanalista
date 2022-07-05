package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Distributor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class EquipmentResponse {
    private Long id;
    private String patrimony;
    private String code;
    private String description;
    private String power;
    private String manufacturingDate;
    private String serie;
    private String prop;
    private String situation;
    private Integer doors;
    private String observation;
    private Distributor distributor;
}
