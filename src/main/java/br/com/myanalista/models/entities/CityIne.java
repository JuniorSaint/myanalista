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
@Table(name = "cityIne")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityIne implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String code;
    private String city;
    private String cityIne;  
}
