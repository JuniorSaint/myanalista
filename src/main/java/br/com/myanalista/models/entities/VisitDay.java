package br.com.myanalista.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.lang.Nullable;

import java.io.Serializable;

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
  @Nullable
  private String daysOfWeek;
  @Nullable
  private String firstDay;
  @Nullable
  private String secondDay;
  @Nullable
  private String thirdDay;
  @Nullable
  private String fourthDay;
  @Nullable
  private String fifthDay;
  @Nullable
  private String sixDay;
}
