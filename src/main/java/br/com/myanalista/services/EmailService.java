package br.com.myanalista.services;

import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.SenderEmail;
import br.com.myanalista.models.enums.StatusEmailEnum;
import br.com.myanalista.repositories.EmailRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmailService {
    private EmailRepository repository;
    private JavaMailSender emailSender;
    private ModelMapper mapper;

    public ResponseEntity<SenderEmail> sendEmail(SenderEmail senderEmail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);

            mimeMessageHelper.setFrom(senderEmail.getEmailFrom());
            mimeMessageHelper.setTo(senderEmail.getEmailTo());
            mimeMessageHelper.setSubject(senderEmail.getSubject());
            mimeMessageHelper.setText(senderEmail.getText());
            emailSender.send(message);

            senderEmail.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e) {
            senderEmail.setStatusEmail(StatusEmailEnum.ERROR);
        } finally {
            return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(senderEmail));
        }
    }

    public ResponseEntity<Page<SenderEmail>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findAll(pageable));
    }

    public ResponseEntity<SenderEmail> findById(Long emailId) {
        Optional<SenderEmail> response = repository.findById(emailId);
        if (response.isPresent()) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(response.get());
        } else {
            throw new EntityNotFoundException("Email not found with id: " + emailId);
        }
    }
}
