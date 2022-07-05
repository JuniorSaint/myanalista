package br.com.myanalista.models.response;

import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.models.entities.SellOut;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ClusterResponse {
    private Long id;
    private String clusterGec;
    private String gecIne;
}
