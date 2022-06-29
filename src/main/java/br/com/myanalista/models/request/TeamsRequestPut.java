package br.com.myanalista.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class TeamsRequestPut extends TeamsRequestPost {
    @NotEmpty(message = "Id is a mandatory field.")
    private Long id;
}
