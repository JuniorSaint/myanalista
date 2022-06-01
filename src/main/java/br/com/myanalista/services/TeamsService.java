package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.CustomersEnity;
import br.com.myanalista.models.entities.TeamsEntity;
import br.com.myanalista.models.request.TeamsRequestPost;
import br.com.myanalista.models.request.TeamsRequestPut;
import br.com.myanalista.models.response.TeamsResponse;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class TeamsService {
  @Autowired
  private TeamsRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private CustomerService serviceCustomer;

  @Transactional
  public TeamsResponse save(TeamsRequestPost teamsRequest) {
    // CustomersEnity entityCustomer = serviceCustomer.findByIdEntity(teamsRequest.getCustomer());
    TeamsEntity teamsEntity = new TeamsEntity();
    mapper.map(teamsRequest, teamsEntity);
    // teamsEntity.setCustomer(entityCustomer);
    TeamsEntity teamsCreated = repository.save(teamsEntity);
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teamsCreated, teamsResponse);
    return teamsResponse;
  }

  @Transactional
  public TeamsResponse update(TeamsRequestPut teamsRequest) {
    TeamsEntity teamsEntity = new TeamsEntity();
    mapper.map(teamsRequest, teamsEntity);
    TeamsEntity teamsUpdate = repository.save(teamsEntity);
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teamsUpdate, teamsResponse);
    return teamsResponse;
  }

  @Transactional
  public String delete(Long id) {
    Optional<TeamsEntity> teams = repository.findById(id);
    if (!teams.isPresent()) {
      throw new BusinessException("Teams not found with id: " + id);
    }
    repository.deleteById(id);
    return "Teams deleted with success";
  }

  public TeamsResponse findById(Long id){
    Optional<TeamsEntity> teams = repository.findById(id);
    if(!teams.isEmpty()){
      throw new BusinessException("It's not possible find Teams with id: " + id);
    }
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teams, teamsResponse);
    return teamsResponse;
  }  
}
