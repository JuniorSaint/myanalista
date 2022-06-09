package br.com.myanalista.models.response;


import br.com.myanalista.models.entities.Channel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class SubChannelResponse {
  
  private String code;
  private String subChannel;
  private String subChannelType;
  private String focusRefPet;
  private String focusDual;
  private String subChannelIne;
  private Channel channel;
}
