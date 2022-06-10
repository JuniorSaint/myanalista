package br.com.myanalista.models.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "subChannel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubChannel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String code;
  private String subChannel;
  private String subChannelType;
  private String focusRefPet;
  private String focusDual;
  private String subChannelIne;
  private Channel channel;

  @OneToOne(mappedBy = "channel")
  private SellOut sellOut;
}
