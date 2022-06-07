package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Customers;
import br.com.myanalista.models.entities.Teams;
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
    Customers entityCustomer = serviceCustomer.findByIdEntity(teamsRequest.getCustomer().getId());
    if (entityCustomer == null) {
      throw new BusinessException("There's not Customer with id: " + teamsRequest.getCustomer().getId());
    }
    Teams teamsEntity = new Teams();
    mapper.map(teamsRequest, teamsEntity);
    teamsEntity.setCustomer(entityCustomer);
    Teams teamsCreated = repository.save(teamsEntity);
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teamsCreated, teamsResponse);
    return teamsResponse;
  }

  @Transactional
  public TeamsResponse update(TeamsRequestPut teamsRequest) {
    Customers entityCustomer = serviceCustomer.findByIdEntity(teamsRequest.getCustomer().getId());
    if (entityCustomer == null) {
      throw new BusinessException("There's not Customer with id: " + teamsRequest.getCustomer().getId());
    }
    Teams teamsEntity = new Teams();
    mapper.map(teamsRequest, teamsEntity);
    teamsEntity.setCustomer(entityCustomer);
    Teams teamsUpdate = repository.save(teamsEntity);
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teamsUpdate, teamsResponse);
    return teamsResponse;
  }

  @Transactional
  public String delete(String code) {
    Optional<Teams> teams = repository.findByMemberCode(code);
    if (!teams.isPresent()) {
      throw new BusinessException("Teams not found with code: " + code);
    }
    repository.deleteByMemberCode(code);
    return "Teams deleted with success";
  }

  public TeamsResponse findByMemberCode(String code) {
    Optional<Teams> teams = repository.findByMemberCode(code);
    if (teams.isEmpty()) {
      throw new BusinessException("It's not possible find Teams with code: " + code);
    }
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teams.get(), teamsResponse);
    return teamsResponse;
  }
}
