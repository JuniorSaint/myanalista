package br.com.myanalista.models.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "visitDay")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitDay implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String daysOfWeek;
  private String firstDay;
  private String secondDay;
  private String thirdDay;
  private String fourthDay;
  private String fifthDay;
  private String sixDay;
}
