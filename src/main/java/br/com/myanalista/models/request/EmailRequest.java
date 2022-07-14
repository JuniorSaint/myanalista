package br.com.myanalista.models.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailRequest {

    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
