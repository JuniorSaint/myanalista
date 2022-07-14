package br.com.myanalista.controllers;

import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.models.entities.SenderEmail;
import br.com.myanalista.models.request.EmailRequest;
import br.com.myanalista.services.EmailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 60 * 60)
@RequestMapping("/v1/sending-email")
@AllArgsConstructor
public class EmailController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    EmailService service;

    @PostMapping
    public ResponseEntity<SenderEmail> sendingEmail(@RequestBody @Valid EmailRequest request) {
        try {
            SenderEmail senderEmail = new SenderEmail();

            return service.sendEmail(mapper.map(request, SenderEmail.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<SenderEmail>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/search")
    public ResponseEntity<SenderEmail> getOneEmail(@RequestParam Optional<Long> emailId) {
        try {
            if (emailId.isEmpty()) {
                throw  new BadRequestException("It's not allowed id empty");
            }
            return service.findById(emailId.get());
        } catch (NoSuchElementException e) {
            throw  new BadRequestException("It's not allowed id empty");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}