package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Channel;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class SubChannelResponse extends RepresentationModel<SubChannelResponse> {
  private Long id;
  private String code;
  private String subChannel;
  private String subChannelType;
  private String focusRefPet;
  private String focusDual;
  private String subChannelIne;
  private Channel channel;
}
