package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Entity
@Table(name = "subChannel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
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
  @JsonIgnore
  @JoinColumn(name = "channel_id")
  private Channel channel;

  @Singular
  @OneToMany(mappedBy = "channel")
  private List<SellOut> sellOuts;

  @OneToMany(mappedBy = "subChannel")
  private List<Lending> lending;

  @OneToMany(mappedBy = "subChannel")
  private List<Customer> customer;
}

