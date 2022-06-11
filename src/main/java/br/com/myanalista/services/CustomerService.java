package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Channel;
import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.models.response.CustomerResponse;
import br.com.myanalista.repositories.ChannelRepository;
import br.com.myanalista.repositories.ClusterGecRepository;
import br.com.myanalista.repositories.CustomerRepository;
import br.com.myanalista.repositories.SubChannelRepository;
import br.com.myanalista.repositories.TeamsRepository;
import org.modelmapper.ModelMapper;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository repository;

  @Autowired
  private SubChannelRepository repositorySubChannel;

  @Autowired
  private ClusterGecRepository repositoryCluster;

  @Autowired
  private TeamsRepository repositoryTeams;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private ChannelRepository repositoryChannel;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/Clientes_06187813000119.CSV";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {
        String[] vector = line.split(";");

        Boolean isExist = ifCustomerExist(vector[0].trim(), vector[38].trim());
        SubChannel subChannel = findSubChannelBySubChannel(vector[13].trim());
        ClusterGec clusterGec = findClusterByClusterGec(vector[28].trim());
        Teams teams = findTeamsMemberByCode(vector[19]);
        Teams teams2 = findTeamsMemberByCode(vector[35]);

        if (!isExist) {
          Customer channel = Customer.builder()
              .code(vector[0].trim())
              .route(vector[1].trim())
              .cnpj(vector[2].trim())
              .inscrEstadual(vector[3].trim())
              .companyName(vector[4].trim())
              .fantasyName(vector[5].trim())
              .address(vector[6].trim())
              .number(vector[7].trim())
              .city(vector[8].trim())
              .state(vector[9].trim())
              .zipCode(vector[10].trim())
              .district(vector[11].trim())
              .phoneNumber(vector[12].trim())
              .subChannel(subChannel)
              .week(vector[14].trim())
              .sequence(vector[15].trim())
              .email(vector[16].trim())
              .tablePrice(vector[17].trim())
              .groupBusiness(vector[18].trim())
              .seller(teams)
              .supervisor(vector[20].trim())
              .area(vector[21].trim())
              .originalPaymentMethod(vector[22].trim())
              .maximunDays(vector[23].trim())
              .turnover(vector[24].trim())
              .regiterDay(vector[25].trim())
              .inactivationDay(vector[26].trim())
              .status(vector[27].trim())
              .clusterGec(clusterGec)
              .refPet(vector[29].trim())
              .ls(vector[30].trim())
              .rgb(vector[31].trim())
              .lastPurchase(vector[32].trim())
              .creditLimit(vector[33].trim())
              .addition(vector[34].trim())
              .seller2(teams2)
              .week2(vector[36].trim())
              .turnover2(vector[37].trim())
              .distributor(vector[38].trim())
              .latitude(vector[39].trim())
              .longitude(vector[40].trim())
              .notAllowCurrentRestChange(vector[41].trim())
              .notChargeTicketBillingFee(vector[42].trim())
              .cidCode(vector[43].trim())
              .currentPaymentMethod(vector[44].trim())
              .coverage(vector[45].trim())
              .phoneNumber2(vector[46].trim())
              .phoneNumber3(vector[47].trim())
              .phoneNumber4(vector[48].trim())
              .promoter(vector[49].trim())
              .promoterEq2(vector[50].trim())
              .channel(findChannelByCode(vector[51].trim()))
              .specie(vector[52].trim())
              .build();

          repository.save(channel);

          line = br.readLine();
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }

  private boolean ifCustomerExist(String code, String distributor) {
    Optional<Customer> response = repository.findByCodeByDistributor(code.trim(), distributor.trim());
    if (response.isPresent()) {
      return true;
    }
    return false;
  }

  private SubChannel findSubChannelBySubChannel(String name) {
    Optional<SubChannel> response = repositorySubChannel.findSubChannelBysubChannel(name.trim());
    if (response.isPresent()) {
      return response.get();
    }
    return null;
  }

  private ClusterGec findClusterByClusterGec(String clusterGec) {
    Optional<ClusterGec> response = repositoryCluster.findByClusterGec(clusterGec.trim());
    if (response.isPresent()) {
      return response.get();
    }
    return null;
  }

  private Teams findTeamsMemberByCode(String code) {
    String[] nameCode = code.split("-");
    if (code.toString().trim().equals("N/D")) {
      return null;
    }
    Integer codeInteger = Integer.parseInt(nameCode[0]);
    String nameSaller = nameCode[1].trim();
    Optional<Teams> team = repositoryTeams.findByMemberCode(codeInteger.toString());
    if (team.isPresent()) {
      return team.get();
    }
    Teams teamsBuild = Teams.builder().memberCode(codeInteger.toString()).fullName(nameSaller).build();
    return repositoryTeams.save(teamsBuild);
  }

  public CustomerResponse findCustomerByCode(String code) {
    Optional<Customer> response = repository.findByCode(code);
    if (response.isEmpty()) {
      throw new BusinessException("There isn't customer with code: " + code);
    }
    CustomerResponse customer = new CustomerResponse();
    mapper.map(response.get(), customer);
    return customer;
  }

  private Channel findChannelByCode(String code) {
    if (code.isEmpty()) {
      return null;
    }
    Optional<Channel> responseChannel = repositoryChannel.findChannelByCode(code);
    if(!responseChannel.isPresent()){
      return null;
    }
    return responseChannel.get();
  }
}
