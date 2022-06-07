package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.CustomerFromCustomer;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.repositories.ClusterGecRepository;
import br.com.myanalista.repositories.CustomerFromCustomerRepository;
import br.com.myanalista.repositories.SubChannelRepository;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class CustomerFromCustomerService {
  @Autowired
  private CustomerFromCustomerRepository repository;

  @Autowired
  private SubChannelRepository repositorySubChannel;

  @Autowired
  private ClusterGecRepository repositoryCluster;

  @Autowired
  private TeamsRepository repositoryTeams;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/Clientes.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {
        String[] vector = line.split(";");

        Boolean isExist = ifCustomerExist(vector[0]);
        SubChannel subChannel = findSubChannelByName(vector[13]);
        ClusterGec clusterGec = findClusterByClusterGec(vector[28]);
        Teams teams = findTeamsMemberByCode(vector[19]);
        Teams teams2 = findTeamsMemberByCode(vector[35]);
        // LocalDate lastParchase = formatDate(vector[33]);
        // LocalDate regiterDay = formatDate((vector[26]));
        // LocalDate inactivationDay = formatDate((vector[27]));

        if (!isExist) {
          CustomerFromCustomer channel = CustomerFromCustomer.builder()
              .code(vector[0])
              .route(vector[1])
              .cnpj(vector[2])
              .inscrEstadual(vector[3])
              .companyName(vector[4])
              .fantasyName(vector[5])
              .address(vector[6])
              .number(vector[7])
              .city(vector[8])
              .state(vector[9])
              .zipCode(vector[10])
              .district(vector[11])
              .phoneNumber(vector[12])
              .subChannel(subChannel)
              .week(vector[14])
              .sequence(vector[15])
              .email(vector[16])
              .tablePrice(vector[17])
              .groupBusiness(vector[18])
              .seller(teams)
              .supervisor(vector[20])
              .area(vector[21])
              .originalPaymentMethod(vector[22])
              .maximunDays(vector[23])
              .turnover(vector[24])
              .regiterDay(vector[25])
              .inactivationDay(vector[26])
              .status(vector[27])
              .clusterGec(clusterGec)
              .refPet(vector[29])
              .ls(vector[30])
              .rgb(vector[31])
              .lastPurchase(vector[32])
              .creditLimit(vector[33])
              .addition(vector[34])
              .seller2(teams2)
              .week2(vector[36])
              .turnover2(vector[37])
              .distributor(vector[38])
              .latitude(vector[39])
              .longitude(vector[40])
              .notAllowCurrentRestChange(vector[41])
              .notChargeTicketBillingFee(vector[42])
              .cidCode(vector[43])
              .currentPaymentMethod(vector[44])
              .coverage(vector[45])
              .phoneNumber2(vector[46])
              .phoneNumber3(vector[47])
              .phoneNumber4(vector[48])
              .promoter(vector[49])
              .promoterEq2(vector[50])
              .channel(vector[51])
              .specie(vector[52])
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

  private boolean ifCustomerExist(String code) {
    Optional<CustomerFromCustomer> response = repository.findByCode(code);
    if (response.isPresent()) {
      return true;
    }
    return false;
  }

  private SubChannel findSubChannelByName(String name) {
    Optional<SubChannel> response = repositorySubChannel.findBySubChannel(name);
    if (response.isPresent()) {
      return response.get();
    }
    return null;
  }

  private ClusterGec findClusterByClusterGec(String clusterGec) {
    Optional<ClusterGec> response = repositoryCluster.findByClusterGec(clusterGec);
    if (response.isPresent()) {
      return response.get();
    }
    return null;
  }

  private Teams findTeamsMemberByCode(String code) {
    String[] nameCode = code.split("-");
    if(code.toString().trim().equals("N/D")){
      return null;
    }
    Integer codeInteger = Integer.parseInt(nameCode[0]);
    String nameSaller = nameCode[1];
    Optional<Teams> team = repositoryTeams.findByMemberCode(codeInteger.toString());
    if (team.isPresent()) {
      return team.get();
    }
    Teams teamsBuild = Teams.builder().memberCode(codeInteger.toString()).fullName(nameSaller).build();
    return repositoryTeams.save(teamsBuild);
  }

  // private LocalDate formatDate(String date) {    
  //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yy");
  //   LocalDate localDate = LocalDate.parse(date, formatter);
  //   return localDate;
  // }
}
