package br.com.myanalista.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Entity
@Table(name = "channel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Channel implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String code;
  private String channel;

  @OneToMany(mappedBy="channel")
  @JsonIgnoreProperties(value = {"channel"})
  private List<SubChannel> subChannels;

  @OneToMany(mappedBy = "channel")
  @JsonIgnoreProperties(value = {"channel"})
  private List<Customer> customer;
}