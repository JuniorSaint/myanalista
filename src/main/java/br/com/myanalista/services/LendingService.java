package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import javax.swing.event.MouseInputAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.CustomerFromCustomer;
import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.repositories.ClusterGecRepository;
import br.com.myanalista.repositories.CustomerFromCustomerRepository;
import br.com.myanalista.repositories.EquipmentRepository;
import br.com.myanalista.repositories.LendingRepository;
import br.com.myanalista.repositories.SubChannelRepository;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class LendingService {
  @Autowired
  private LendingRepository repository;

  @Autowired
  private CustomerFromCustomerRepository repositoryCustomer;

  @Autowired
  private ClusterGecRepository repositoryCluster;

  @Autowired
  private SubChannelRepository repositorySub;

  @Autowired
  private EquipmentRepository repositoryEquipment;

  @Autowired
  private TeamsRepository repositoryTeams;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/COMODATOS.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        Lending channel = Lending.builder()
            .territory(vector[0])
            .dealer(vector[1])
            .customerRegistration(findCustomer(vector[2]))
            .gec(findCluster(vector[7]))
            .subChannel(findSubChannel(vector[8]))
            .city(vector[9])
            .equipmentNumber(findEquipmentByCode(vector[10]))
            .contract(vector[14])
            .amount(Integer.parseInt(vector[17]))
            .dateSend(convertDate(vector[18]))
            .dueDate(convertDate(vector[19]))
            .sellerCode(findSeller(vector[20]))
            .route(vector[21])
            .nfe(vector[22])
            .conservation(vector[23])
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }

  }

  private CustomerFromCustomer findCustomer(String customer) {
    if (customer.isEmpty()) {
      return null;
    }
    Optional<CustomerFromCustomer> customerResponse = repositoryCustomer.findByCode(customer);
    if (!customerResponse.isPresent()) {
      return null;
    }
    return customerResponse.get();
  }

  private ClusterGec findCluster(String cluster) {
    if (cluster.isEmpty()) {
      return null;
    }
    Optional<ClusterGec> clusterResponse = repositoryCluster.findByClusterGec(cluster);
    if (!clusterResponse.isPresent()) {
      return null;
    }
    return clusterResponse.get();
  }

  private SubChannel findSubChannel(String sub) {
    if (sub.isEmpty()) {
      return null;
    }
    Optional<SubChannel> subResponse = repositorySub.findSubChannelBysubChannel(sub);
    if (!subResponse.isPresent()) {
      return null;
    }
    return subResponse.get();
  }

  private Equipment findEquipmentByCode(String code) {
    if (code.isEmpty()) {
      return null;
    }
    Optional<Equipment> responseEquipment = repositoryEquipment.findByCode(code);
    if (responseEquipment.isEmpty()) {
      return null;
    }
    return responseEquipment.get();
  }

  private LocalDate convertDate(String date) {
    String day = "";
    String month = "";
    if (date.isEmpty()) {
      return null;
    }
    String[] splitString = date.split("/");
    if (splitString[0].isEmpty()) {
      return null;
    }
    if (splitString[0].length() < 2) {
      day = "0" + splitString[0];
    } else {
      day = splitString[0];
    }
    if (splitString[1].length() < 2) {
      month = "0" + splitString[1];
    } else {
      month = splitString[1];
    }
    String dateString = splitString[2] + "-" + month + "-" + day;
    LocalDate dateConverted = LocalDate.parse(dateString);
    return dateConverted;
  }

  private Teams findSeller(String seller) {
    if (seller.isEmpty()) {
      return null;
    }
    Optional<Teams> sellerResponse = repositoryTeams.findByMemberCode(seller);
    if (!sellerResponse.isPresent()) {
      return null;
    }
    return sellerResponse.get();
  }
}

// }Território;Distribuidor;Matrícula Cliente;CPF;CNPJ;Razão Social;Nome
// Fantasia;GEC;SubCanal;Cidade;Nr Equipamento;Série;Quant.
// Portas;Logomarca;Contrato;Código;Produto;Qtde;Emissão;Vencimento;Vendedor;Rota;NFe;Conservação
