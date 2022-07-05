package br.com.myanalista.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactDepartament;
    private String contactEmail;
    private String contactName;
    private String contactPhone;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="distributor_id", nullable=true)
    private Distributor distributor;

}
