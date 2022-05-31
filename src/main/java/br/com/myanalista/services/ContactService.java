package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.ContactsEntity;
import br.com.myanalista.models.request.ContactRequestPost;
import br.com.myanalista.models.request.ContactRequestPut;
import br.com.myanalista.models.response.ContactResponse;
import br.com.myanalista.repositories.ContactRepository;

@Service
public class ContactService {

  @Autowired
  private ContactRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Transactional
  public ContactResponse save(ContactRequestPost contactRequest) {
      ContactsEntity contactEntity = new ContactsEntity();
      mapper.map(contactRequest, contactEntity);
      ContactsEntity contactCreated = repository.save(contactEntity);
      ContactResponse contactResponse = new ContactResponse();
      mapper.map(contactCreated, contactResponse);
      return contactResponse;    
  }

  @Transactional
  public ContactResponse update(ContactRequestPut contactRequest) {
    ContactsEntity contactEntity = new ContactsEntity();
      mapper.map(contactRequest, contactEntity);
      ContactsEntity contactUpdate = repository.save(contactEntity);
      ContactResponse contactResponse = new ContactResponse();
      mapper.map(contactUpdate, contactResponse);
      return contactResponse;    
  }

  @Transactional
  public String delete(Long id) throws Exception {
      Optional<ContactsEntity> contact = repository.findById(id);
      if (!contact.isPresent()) {
        throw new BusinessException("Contact not found with id: " + id);
      }
      repository.deleteById(id);
      return "Contact deleted with success";    
  }

  public ContactResponse findById(Long id){
    Optional<ContactsEntity> contact = repository.findById(id);
    if(!contact.isEmpty()){
      throw new BusinessException("It's not possible find contact with id: " + id);
    }
    ContactResponse contactResp = new ContactResponse();
    mapper.map(contact, contactResp);
    return contactResp;
  }  
}
