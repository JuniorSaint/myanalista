package br.com.myanalista.models.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Builder
@Entity
@Table(name = "Turnover")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Turnover implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate calendarDate;
    private String turnoverByCalendar;
}
