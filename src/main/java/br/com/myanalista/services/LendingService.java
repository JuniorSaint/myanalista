package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.models.entities.Lending;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.repositories.CustomerRepository;
import br.com.myanalista.repositories.DistributorRepository;
import br.com.myanalista.repositories.EquipmentRepository;
import br.com.myanalista.repositories.LendingRepository;
import br.com.myanalista.repositories.SubChannelRepository;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class LendingService {
  @Autowired
  private LendingRepository repository;

  @Autowired
  private CustomerRepository repositoryCustomer;

  @Autowired
  private SubChannelRepository repositorySub;

  @Autowired
  private EquipmentRepository repositoryEquipment;

  @Autowired
  private TeamsRepository repositoryTeams;

  @Autowired
  private DistributorRepository repositoryDistributor;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/06187813000119_COMODATOS_RAF.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        int index_1 = line.indexOf(";");
        int index_2 = line.indexOf(";", index_1 + 1);
        int index_3 = line.indexOf(";", index_2 + 1);
        int index_4 = line.indexOf(";", index_3 + 1);
        int index_5 = line.indexOf(";", index_4 + 1);
        int index_7 = line.indexOf(";", index_5 + 1);
        int index_8 = line.indexOf(";", index_7 + 1);
        int index_9 = line.indexOf(";", index_8 + 1);
        int index_10 = line.indexOf(";", index_9 + 1);
        int index_11 = line.indexOf(";", index_10 + 1);
        int index_12 = line.indexOf(";", index_11 + 1);
        int index_13 = line.indexOf(";", index_12 + 1);
        int index_14 = line.indexOf(";", index_13 + 1);
        int index_15 = line.indexOf(";", index_14 + 1);
        int index_16 = line.indexOf(";", index_15 + 1);
        int index_17 = line.indexOf(";", index_16 + 1);
        int index_18 = line.indexOf(";", index_17 + 1);
        int index_19 = line.indexOf(";", index_18 + 1);
        int index_20 = line.indexOf(";", index_19 + 1);
        int index_21 = line.indexOf(";", index_20 + 1);
        int index_22 = line.indexOf(";", index_21 + 1);
        int index_23 = line.indexOf(";", index_22 + 1);
        int index_24 = line.indexOf(";", index_23 + 1);

        Lending channel = Lending.builder()
            .territory(line.substring(0, index_1).trim())
            .distributor(findDistributor(line.substring(index_1 + 1, index_2).trim()))
            .customerRegistration(findCustomer(line.substring(index_2 + 1, index_3).trim()))
            .gec(line.substring(index_8 + 1, index_9).trim())
            .subChannel(findSubChannel(line.substring(index_9 + 1, index_10).trim()))
            .city(line.substring(index_10 + 1, index_11).trim())
            .equipmentNumber(findEquipmentByCode(line.substring(index_11 + 1, index_12).trim()))
            .contract(line.substring(index_15 + 1, index_16).trim())
            .amount(Integer.parseInt(line.substring(index_18 + 1, index_19).trim()))
            .dateSend(convertDate(line.substring(index_19 + 1, index_20).trim()))
            .dueDate(convertDate(line.substring(index_20 + 1, index_21).trim()))
            .sellerCode(findSeller(line.substring(index_21 + 1, index_22).trim()))
            .route(line.substring(index_22 + 1, index_23).trim())
            .nfe(line.substring(index_23 + 1, index_24).trim())
            .conservation(line.substring(index_24 + 1).trim())
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }

  }

  private Customer findCustomer(String customer) {
    if (customer.isEmpty()) {
      return null;
    }
    Optional<Customer> customerResponse = repositoryCustomer.findByCode(customer);
    if (!customerResponse.isPresent()) {
      return null;
    }
    return customerResponse.get();
  }

  private Distributor findDistributor(String cnpj) {
    if (cnpj.isEmpty()) {
      return null;
    }
    Optional<Distributor> response = repositoryDistributor.findDistributorByCnpj(cnpj);
    if (!response.isPresent()) {
      return null;
    }
    return response.get();
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