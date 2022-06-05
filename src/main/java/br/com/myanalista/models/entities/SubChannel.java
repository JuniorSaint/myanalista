package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

@Builder
@Entity
@Table(name = "subchannel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubChannel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String subChannel;
  private String subChannelType;
  private String refPetFocus;
  private String dualFocus;
  private String subChannelIne;
}
