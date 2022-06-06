package br.com.myanalista.models.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
@Entity
@Table(name = "nationalHolidays")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationalHolidays implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private String dayOfTheWeek;
    private String month;
    private String description;
    private String city;
    private String territory;
    private String year;
    private String fixedDay;
  
}
