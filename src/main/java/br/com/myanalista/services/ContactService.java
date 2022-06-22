package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import br.com.myanalista.models.response.ContactSearchResponse;
import br.com.myanalista.models.response.DistributorSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Contacts;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.request.ContactRequestPost;
import br.com.myanalista.models.request.ContactRequestPut;
import br.com.myanalista.models.response.ContactResponse;
import br.com.myanalista.repositories.ContactRepository;
import br.com.myanalista.repositories.DistributorRepository;

@Service
public class ContactService {

  @Autowired
  private ContactRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Autowired DistributorRepository repositoryDistributor;

  @Transactional
  public ContactResponse save(ContactRequestPost contactRequest) {
   Optional<Distributor> distributor = repositoryDistributor.findDistributorById(contactRequest.getDistributor().getId());
    if (!distributor.isPresent()) {
      throw new BusinessException("There's not Customer with id: " + contactRequest.getDistributor().getId());
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
   Optional<Distributor> distributor = repositoryDistributor.findDistributorById(contactRequest.getDistributor().getId());
    if (!distributor.isPresent()) {
      throw new BusinessException("There's not Customer with id: " + contactRequest.getDistributor().getId());
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
  public String delete(Long id) {
      Optional<Contacts> contact = repository.findById(id);
      if (!contact.isPresent()) {
        throw new BusinessException("Contact not found with id: " + id);
      }
      repository.deleteById(id);
      return "Contact deleted with success";    
  }

  public ContactResponse findById(Long id){
    Optional<Contacts> contact = repository.findById(id);
    if(contact.isEmpty()){
      throw new BusinessException("It's not possible find contact with id: " + id);
    }
    ContactResponse contactResp = new ContactResponse();
    mapper.map(contact.get(), contactResp);
    return contactResp;
  }

    public Page<ContactSearchResponse> listOfDistributor(Contacts contacts, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Contacts> example = Example.of(contacts, matcher);

        Page<ContactSearchResponse> response = repository.findAllPageableAndSort(pageable, example);
        return response;
    }
}
