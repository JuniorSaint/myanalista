package br.com.myanalista.models.request;


import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryImportFileRequest {
    private String categorySon;
    private String categoryFather;
    private String categoryGrand;
}
