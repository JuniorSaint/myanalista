package br.com.myanalista.repositories;

import br.com.myanalista.models.entities.SenderEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<SenderEmail, Long> {
}
