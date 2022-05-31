package br.com.myanalista.models.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.myanalista.models.enums.UserTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private UserTypeEnum userType;
    private String userEmail;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}