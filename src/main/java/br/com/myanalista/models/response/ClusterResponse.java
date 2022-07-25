package br.com.myanalista.models.response;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ClusterResponse extends RepresentationModel<ClusterResponse> {
    private Long id;
    private String clusterGec;
    private String gecIne;
}
