package br.com.myanalista.configs;

import br.com.myanalista.models.entities.SenderEmail;
import br.com.myanalista.models.response.CriticizeFieldsResponse;
import br.com.myanalista.services.EmailService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public class EmailStandard {
    @Autowired
    private EmailService service;
    String pattern = " dd 'de' MMMM 'de' YYYY ";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);

    public void senderEmail(CriticizeFieldsResponse criticizeFieldsResponse) {
        SenderEmail senderEmail = SenderEmail.builder()
                .emailTo("junior.garbage@gmail.com")
                .emailFrom("contato@idip.com.br")
                .subject("<div> <strong> Relatório de carga do cliente: </strong> " + criticizeFieldsResponse.getDistributor() + ". </div>")
                .text("<br>" +
                        "<div>  <strong>" + sdf.format(new LocalDate()) + "</strong> </div>"
                        + "<br> <br>"
                        + "<div> Abaixo Relação de observações encontradas no importe do dia. </div>"
                        + "<div> <stong> CNPJ da distribuidora:  </strong> " + criticizeFieldsResponse.getCnpj() + ". </div>"
                        + "<br>"
                        + "<div> ----------------------------------------------------------------------- </div>"
                        + "<br>"
                        + criticizeFieldsResponse.getCriticizes())
                .build();
        service.sendEmail(senderEmail);
    }

}
