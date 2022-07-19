package br.com.myanalista.models.entities;

import br.com.myanalista.models.enums.StatusEmailEnum;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "senderEmail")
@Builder
public class SenderEmail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Lob
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmailEnum statusEmail;
}