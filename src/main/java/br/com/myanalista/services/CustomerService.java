package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Customers;
import br.com.myanalista.models.request.CustomerRequestPost;
import br.com.myanalista.models.request.CustomerRequestPut;
import br.com.myanalista.models.response.CustomerResponse;
import br.com.myanalista.repositories.CustomerRepository;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Transactional
  public CustomerResponse save(CustomerRequestPost customerRequest) {
    Customers customerEntity = new Customers();
    mapper.map(customerRequest, customerEntity);
    Customers customerCreated = repository.save(customerEntity);
    CustomerResponse customerResponse = new CustomerResponse();
    mapper.map(customerCreated, customerResponse);
    return customerResponse;
  }

  @Transactional
  public CustomerResponse update(CustomerRequestPut contactRequest) {
    Customers customerEntity = new Customers();
      mapper.map(contactRequest, customerEntity);
      Customers customerUpdate = repository.save(customerEntity);
      CustomerResponse customerResponse = new CustomerResponse();
      mapper.map(customerUpdate, customerResponse);
      return customerResponse;    
  }

  @Transactional
  public String delete(Long id) {
      Optional<Customers> contact = repository.findById(id);
      if (!contact.isPresent()) {
        throw new BusinessException("Customer not found with id: " + id);
      }
      repository.deleteById(id);
      return "Customer deleted with success";    
  }

  public CustomerResponse findById(Long id){
    Optional<Customers> customer = repository.findById(id);
    if(customer.isEmpty()){
      throw new BusinessException("It's not possible find customer with id: " + id);
    }
    CustomerResponse customerResponse = new CustomerResponse();
    mapper.map(customer.get(), customerResponse);
    return customerResponse;
  }

  public Customers findByIdEntity(Long id){
    Optional<Customers> customer = repository.findById(id);
    if(customer.isEmpty()){
      throw new BusinessException("It's not possible find customer with id: " + id);
    }
    return customer.get();
  }

}
