package br.com.myanalista.models.entities;


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
public class ContactEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactDepartament;
    private String contactEmail;
    private String contactName;
    private String contactPhone;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEnity customer;

}
