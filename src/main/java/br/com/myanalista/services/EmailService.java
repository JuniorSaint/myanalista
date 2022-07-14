package br.com.myanalista.services;

import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.SenderEmail;
import br.com.myanalista.models.enums.StatusEmailEnum;
import br.com.myanalista.repositories.EmailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailService {
    @Autowired
    private EmailRepository repository;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private ModelMapper mapper;

    public ResponseEntity<SenderEmail> sendEmail(SenderEmail senderEmail) {

        senderEmail.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail.getEmailFrom());
            message.setTo(senderEmail.getEmailTo());
            message.setSubject(senderEmail.getSubject());
            message.setText(senderEmail.getText());
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
