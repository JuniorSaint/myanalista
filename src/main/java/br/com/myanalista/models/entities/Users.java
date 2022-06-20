package br.com.myanalista.models.entities;

import br.com.myanalista.models.enums.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    @NotEmpty(message = "Password is mandatory field")
    private String password;
    @NotEmpty(message = "Administrator is mandatory field")
    private UserTypeEnum userType;
    @NotEmpty(message = "Email is mandatory field")
    private String userEmail;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
}