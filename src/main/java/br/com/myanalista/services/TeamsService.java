package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.myanalista.models.response.DistributorSearchResponse;
import br.com.myanalista.models.response.TeamsSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private DistributorRepository repositoryDistributor;

    @Transactional
    public TeamsResponse save(TeamsRequestPost teamsRequest) {
        if (teamsRequest.getDistributor().getId() == null || teamsRequest.getMemberCode() == null) {
            throw new BusinessException("Fields memberCode and distributor id is mandatory");
        }

//        The code needs to have 3 digitals, because in the file sellout the code has 3 digital
        String newCodeMember = teamsRequest.getMemberCode();
        for (Integer x = 0; x < (3 - teamsRequest.getMemberCode().length()); x++) {
            newCodeMember = "0".concat(newCodeMember);
        }
        teamsRequest.setMemberCode(newCodeMember);

        Optional<Distributor> distributor = repositoryDistributor
                .findDistributorById(teamsRequest.getDistributor().getId());
        if (!distributor.isPresent()) {
            throw new BusinessException("There's not distributor with id: " + teamsRequest.getDistributor().getCnpjCpf());
        }

        Optional<Teams> teamsResponseTest = repository.findMemberCodeAndDistributor(teamsRequest.getMemberCode(), distributor.get());
        if (teamsResponseTest.isPresent()) {
            throw new BusinessException("There is a team member with memberCode " + teamsRequest.getMemberCode() + ", and distributor id: " + teamsRequest.getDistributor().getId());
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
        if (teamsRequest.getDistributor().getId() == null || teamsRequest.getMemberCode() == null) {
            throw new BusinessException("Fields memberCode and distributor id is mandatory");
        }

//        The code needs to have 3 digitals, because in the file sellout the code has 3 digital
        String newCodeMember = teamsRequest.getMemberCode();
        for (Integer x = 0; x < (3 - teamsRequest.getMemberCode().length()); x++) {
            newCodeMember = "0".concat(newCodeMember);
        }
        teamsRequest.setMemberCode(newCodeMember);

        Optional<Distributor> distributor = repositoryDistributor
                .findDistributorById(teamsRequest.getDistributor().getId());
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

    public Page<TeamsSearchResponse> listOfDistributor(Teams teams, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase();
        Example<Teams> example = Example.of(teams, matcher);

        Page<TeamsSearchResponse> response = repository.findAllPageableAndSort(pageable, example);
        return response;
    }

    public void recordDataToDb() throws IOException {
        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/VENDEDORES.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();
            while (line != null) {

                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);
                int index_3 = line.indexOf(";", index_2 + 1);
                int index_4 = line.indexOf(";", index_3 + 1);


                boolean isExist = ifTeamsRegistered(line.substring(index_1 + 1, index_2).trim(),
                        line.substring(0, index_1).trim());

                if (!isExist) {

                    Teams channelResp = Teams.builder()
                            .distributor(findDistributorByCnpj(line.substring(0, index_1).trim()))
                            .memberCode(verifySizeCode(line.substring(index_1 + 1, index_2).trim()))
                            .fullName(line.substring(index_2 + 1, index_3).trim())
                            .memberFunction(line.substring(index_3 + 1, index_4).trim())
                            .sellerOrSupervisor("vendedor")
                            .build();

                    repository.save(channelResp);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private String verifySizeCode(String code) {
        String result = code;
        for (Integer x = 0; x < (3 - code.length()); x++) {
            result = "0".concat(result);
        }
        return result;
    }

    private boolean ifTeamsRegistered(String code, String cnpj) {
//      Return true because can't register team without code or cnpj
        if (code.isEmpty() && cnpj.isEmpty()) {
            return true;
        }
//        code is mandatory with 3 digitals
        for (int x = 0; x < (3 - code.length()); x++) {
            code = "0".concat(code);
        }
//        cnpj in the file when start with 0, the zerou disappear
        if (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }
        Optional<Distributor> responseDistributor = repositoryDistributor.findDistributorByCnpj(cnpj);
        if (!responseDistributor.isPresent()) {
            throw new BusinessException("Distributor not found with cnpj: " + cnpj);
        }
        Optional<Teams> responseTeam = repository.findMemberCodeAndDistributor(code, responseDistributor.get());
        if (responseTeam.isPresent()) {
            return true;
        }
        return false;
    }

    private Distributor findDistributorByCnpj(String cnpj) {
        if (cnpj.isEmpty()) {
            return null;
        }
        //        cnpj in the file when start with 0, the zerou disappear
        if (cnpj.length() < 14) {
            cnpj = "0" + cnpj;
        }

        Optional<Distributor> responseDistributor = repositoryDistributor.findDistributorByCnpj(cnpj);
        if (!responseDistributor.isPresent()) {
            return null;
        }
        return responseDistributor.get();
    }
}
