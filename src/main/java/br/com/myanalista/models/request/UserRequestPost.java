package br.com.myanalista.models.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.myanalista.models.entities.Permission;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserRequestPost {
    private String name;
    @NotEmpty(message = "Email is a mandatory field.")
    private String email;
    @NotEmpty(message = "Password is a mandatory field.")
    private String password;
    private Boolean active;
    private List<Permission> permissions;

}
