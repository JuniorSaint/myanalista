package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Distributor;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class EquipmentResponse extends RepresentationModel<EquipmentResponse> {
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
