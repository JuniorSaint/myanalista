package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.request.DistributorRequestPost;
import br.com.myanalista.models.request.DistributorRequestPut;
import br.com.myanalista.models.response.DistributorResponse;
import br.com.myanalista.repositories.DistributorRepository;


@Service
public class DistributorService {
  @Autowired
  private DistributorRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Transactional
  public DistributorResponse save(DistributorRequestPost customerRequest) {
    Distributor distributorEntity = new Distributor();
    mapper.map(customerRequest, distributorEntity);
    Distributor customerCreated = repository.save(distributorEntity);
    DistributorResponse distributorResponse = new DistributorResponse();
    mapper.map(customerCreated, distributorResponse);
    return distributorResponse;
  }

  @Transactional
  public DistributorResponse update(DistributorRequestPut contactRequest) {
    Distributor distributorEntity = new Distributor();
      mapper.map(contactRequest, distributorEntity);
      Distributor customerUpdate = repository.save(distributorEntity);
      DistributorResponse distributorResponse = new DistributorResponse();
      mapper.map(customerUpdate, distributorResponse);
      return distributorResponse;    
  }

  @Transactional
  public String delete(Long id) {
      Optional<Distributor> contact = repository.findById(id);
      if (!contact.isPresent()) {
        throw new BusinessException("Customer not found with id: " + id);
      }
      repository.deleteById(id);
      return "Customer deleted with success";    
  }

  public DistributorResponse findById(Long id){
    Optional<Distributor> distributor = repository.findById(id);
    if(distributor.isEmpty()){
      throw new BusinessException("It's not possible find customer with id: " + id);
    }
    DistributorResponse distributorResponse = new DistributorResponse();
    mapper.map(distributor.get(), distributorResponse);
    return distributorResponse;
  }

  public Distributor findByIdEntity(Long id){
    Optional<Distributor> distributor = repository.findById(id);
    if(distributor.isEmpty()){
      throw new BusinessException("It's not possible find customer with id: " + id);
    }
    return distributor.get();
  }

}
