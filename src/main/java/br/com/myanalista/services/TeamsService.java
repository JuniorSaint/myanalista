package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.models.request.TeamsRequestPost;
import br.com.myanalista.models.request.TeamsRequestPut;
import br.com.myanalista.models.response.TeamsResponse;
import br.com.myanalista.repositories.DistributorRepository;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class TeamsService {
  @Autowired
  private TeamsRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private DistributorRepository  repositoryDistributor;

  @Transactional
  public TeamsResponse save(TeamsRequestPost teamsRequest) {
   Optional<Distributor> distributor = repositoryDistributor.findDistributorByCnpj(teamsRequest.getDistributor().getCnpjCpf());
    if (!distributor.isPresent()) {
      throw new BusinessException("There's not Customer with id: " + teamsRequest.getDistributor().getCnpjCpf());
    }
    Teams teamsEntity = new Teams();
    mapper.map(teamsRequest, teamsEntity);
    teamsEntity.setDistributor(distributor.get());
    Teams teamsCreated = repository.save(teamsEntity);
    TeamsResponse teamsResponse = new TeamsResponse();
    mapper.map(teamsCreated, teamsResponse);
    return teamsResponse;
  }

  @Transactional
  public TeamsResponse update(TeamsRequestPut teamsRequest) {
   Optional<Distributor> distributor = repositoryDistributor.findDistributorByCnpj(teamsRequest.getDistributor().getCnpjCpf());
    if (!distributor.isPresent()) {
      throw new BusinessException("There's not Customer with id: " + teamsRequest.getDistributor().getCnpjCpf());
    }
    Teams teamsEntity = new Teams();
    mapper.map(teamsRequest, teamsEntity);
    teamsEntity.setDistributor(distributor.get());
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
