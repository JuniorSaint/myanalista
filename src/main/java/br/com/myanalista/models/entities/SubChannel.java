package br.com.myanalista.models.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String code;
  private String subChannel;
  private String subChannelType;
  private String focusRefPet;
  private String focusDual;
  private String subChannelIne;
  @ManyToOne
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @OneToMany(mappedBy = "channel")
  private List<SellOut> sellOuts;

  @OneToMany(mappedBy = "subChannel")
  private List<Lending> lending;

  @OneToMany(mappedBy = "subChannel")
  private List<Customer> customer;
}

