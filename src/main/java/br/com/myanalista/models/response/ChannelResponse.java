package br.com.myanalista.models.response;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ChannelResponse extends RepresentationModel<ChannelResponse> {
  private Long id;
  private String code;
  private String channel;
}
