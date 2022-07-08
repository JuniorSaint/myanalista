package br.com.myanalista.models.request;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class UserRequestPost {
    @NotEmpty(message = "User name is a mandatory field.")
    private String userName;
    @NotEmpty(message = "Email is a mandatory field.")
    private String email;
    private String password;
    private Boolean active;

}
