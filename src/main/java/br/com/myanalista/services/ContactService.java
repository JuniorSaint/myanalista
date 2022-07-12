package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.response.ContactSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.request.ContactRequestPost;
import br.com.myanalista.models.request.ContactRequestPut;
import br.com.myanalista.models.response.ContactResponse;
import br.com.myanalista.repositories.ContactRepository;
import br.com.myanalista.repositories.DistributorRepository;
import org.springframework.web.servlet.function.EntityResponse;

@Service
public class ContactService {
    @Autowired
    private ContactRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private Utils utils;
    @Autowired
    DistributorRepository repositoryDistributor;

    @Transactional
    public ContactResponse save(ContactRequestPost contactRequest) {
        Optional<Distributor> distributor = repositoryDistributor.findById(contactRequest.getDistributor().getId());
        if (!distributor.isPresent()) {
            throw new EntityNotFoundException("There's not distributor with id: " + contactRequest.getDistributor().getId());
        }
        Contacts contactEntity = new Contacts();
        mapper.map(contactRequest, contactEntity);
        contactEntity.setDistributor(distributor.get());
        Contacts contactCreated = repository.save(contactEntity);
        ContactResponse contactResponse = new ContactResponse();
        mapper.map(contactCreated, contactResponse);
        return contactResponse;
    }

    @Transactional
    public ContactResponse update(ContactRequestPut contactRequest) {
        Optional<Distributor> distributor = repositoryDistributor.findById(contactRequest.getDistributor().getId());
        if (!distributor.isPresent()) {
            throw new EntityNotFoundException("There's not distributor with id: " + contactRequest.getDistributor().getId());
        }
        Contacts contactEntity = new Contacts();
        mapper.map(contactRequest, contactEntity);
        contactEntity.setDistributor(distributor.get());
        Contacts contactUpdate = repository.save(contactEntity);
        ContactResponse contactResponse = new ContactResponse();
        mapper.map(contactUpdate, contactResponse);
        return contactResponse;
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Optional<Contacts> contact = repository.findById(id);
        if (!contact.isPresent()) {
            throw new EntityNotFoundException("Contact not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Contact deleted with success!");
    }

    public ContactResponse findById(Long id) {
        Optional<Contacts> contact = repository.findById(id);
        if (contact.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find contact with id: " + id);
        }
        ContactResponse contactResp = new ContactResponse();
        mapper.map(contact.get(), contactResp);
        return contactResp;
    }
    public ResponseEntity<Page<ContactSearchResponse>> findAllWithPage(Pageable pageable) {
        Page<Contacts> responses = repository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapEntityPageIntoDtoPage(responses, ContactSearchResponse.class));
    }

    public ResponseEntity<Page<ContactSearchResponse>> findAllWithPageSeek(String search, Pageable pageable) {
        Page<Contacts> responses = repository.findByContactNameOrContactEmailOrContactDepartament(search, pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapEntityPageIntoDtoPage(responses, ContactSearchResponse.class));
    }
}
