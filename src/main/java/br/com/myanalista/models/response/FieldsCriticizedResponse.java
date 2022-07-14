package br.com.myanalista.models.response;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldsCriticizedResponse {
    private String field;
    private String customerName;
}
