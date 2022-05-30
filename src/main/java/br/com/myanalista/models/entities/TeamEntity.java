package br.com.myanalista.models.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String memberCode;
    private String cpf;
    private String memberFunction;
    private String typeOfRegistrationMember;
    private String memberLink;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private CustomerEnity customer;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}
