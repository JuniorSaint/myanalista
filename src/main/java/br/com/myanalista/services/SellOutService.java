package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.entities.Customer;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.entities.SellOut;
import br.com.myanalista.models.entities.SubChannel;
import br.com.myanalista.models.entities.Teams;
import br.com.myanalista.repositories.ClusterGecRepository;
import br.com.myanalista.repositories.CustomerRepository;
import br.com.myanalista.repositories.ProductRepository;
import br.com.myanalista.repositories.SellOutRepository;
import br.com.myanalista.repositories.SubChannelRepository;
import br.com.myanalista.repositories.TeamsRepository;

@Service
public class SellOutService {

  @Autowired
  private SellOutRepository repository;

  @Autowired
  private CustomerRepository repositoryCustomer;

  @Autowired
  private TeamsRepository repositoryTeams;

  @Autowired
  private ClusterGecRepository repositoryCluster;

  @Autowired
  private SubChannelRepository repositorySub;

  @Autowired
  private ProductRepository repositoryProduct;

  public void recordDataToDb() throws IOException {
    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/SELLOUT.CSV";

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {

      String line = br.readLine(); // this first line will be discarted, because is the header.
      line = br.readLine();
      while (line != null) {

        String[] vector = line.split(";");

        SellOut channel = SellOut.builder()
            .distributor(vector[0].trim())
            .date(convertDate(vector[1].trim()))
            .customer(findCustomer(vector[2].trim()))
            .route(vector[3].trim())
            .sellersOrder(findSeller(vector[4].trim()))
            .supervisorsOrder(vector[5].trim())
            .sellerRegistration(findSeller(vector[6].trim()))
            .supervisorRegistration(vector[7].trim())
            .city(vector[8].trim())
            .typeOperation(vector[9].trim())
            .nfNumber(vector[10].trim())
            .product(findProductByCode(vector[11].trim())) // The product must be fixed, because the object is wrong,
                                                           // done to be if the import is correct.
            .amount(Double.parseDouble(vector[12].trim().replaceAll(",", ".")))
            .liter(vector[13].trim())
            .physicalBox(vector[14].trim())
            .condition(vector[15].trim())
            .weight(Double.parseDouble(vector[16].trim().replaceAll(",", ".")))
            .priceSell(Double.parseDouble(vector[17].trim().replaceAll(",", ".")))
            .priceCost(Double.parseDouble(vector[18].trim().replaceAll(",", ".")))
            .tablePrice(vector[19].trim())
            .groupR(vector[20].trim()) // product's group, don't put it()
            .category(vector[21].trim()) // categories don't put it()
            .brand(vector[22].trim()) // Brand don't need to put it()
            .tableSell(Integer.parseInt(vector[23].trim()))
            .cluster(findCluster(vector[24].trim())) // create register from sellout's table()
            .channel(findSubChannel(vector[25].trim())) // channel is consider subchannel to myanlista then put
                                                        // subchannel on channel()
            .averageTerm(Integer.parseInt(vector[26].trim()))
            .cfop(vector[27].trim())
            .fantasy(vector[28].trim())
            .physicalJuridical(vector[29].trim())
            .packages(vector[30].trim())
            .ean(vector[31].trim())
            .ncm(vector[32].trim())
            .cia(vector[33].trim())
            .unit(vector[34].trim())
            .monthYear(vector[35].trim())
            .comission(vector[36].trim())
            .district(vector[37].trim())
            .sellPrice(Double.parseDouble(vector[38].trim().replaceAll(",", ".")))
            .amountReturned(Double.parseDouble(vector[39].trim().replaceAll(",", ".")))
            .literReturned(Integer.parseInt(vector[40].trim().replaceAll(",", ".")))
            .physicalBoxReturned(Integer.parseInt(vector[41].trim()))
            .valueReturned(Double.parseDouble(vector[42].trim().replaceAll(",", ".")))
            .valueMeta(Integer.parseInt(vector[43].trim()))
            .amountMeta(Integer.parseInt(vector[44].trim()))
            .physicalBoxMeta(Integer.parseInt(vector[45].trim()))
            .coverMeta(vector[46])
            .averageTermMeta(vector[47])
            .seller2(findSeller(vector[48].trim()))
            .supervisor2(vector[49].trim())
            .route2(vector[50].trim())
            .quarter(vector[51].trim())
            .orderDate(convertDate(vector[52].trim()))
            .unitBox(Integer.parseInt(vector[53].trim()))
            .unitBoxReturned(Integer.parseInt(vector[54].trim()))
            .unitBoxBox(Double.parseDouble(vector[55].trim().replace(",", ".")))
            .unitBoxRmeta(Integer.parseInt(vector[56].trim()))
            .register(vector[57].trim())
            .area(vector[58].trim())
            .discountCustomer(Integer.parseInt((vector[59].trim())))
            .uf(vector[60].trim())
            .toR(vector[61].trim())
            .map(vector[62].trim())
            .address(vector[63].trim())
            .motiveReturn(vector[64].trim())
            .productType(vector[65].trim())
            .transaction(Integer.parseInt(vector[66].trim()))
            .consumptionType(vector[67].trim())
            .year(vector[68].trim())
            .active(vector[69].trim())
            .purchase(vector[70].trim())
            .equipment(vector[71].trim())
            .gvSup(vector[72].trim())
            .adfFin(Double.parseDouble(vector[73].trim().replace(",", ".")))
            .cobBol(Double.parseDouble(vector[74].trim().replace(",", ".")))
            .companyCategory(vector[75].trim())
            .companySubCategory(vector[76].trim())
            .companyReturnability(vector[77].trim())
            .companyBrand(vector[78].trim())
            .flavor(vector[79].trim())
            .segment(vector[80].trim())
            .consumptionOccasion(vector[81].trim())
            .nfeDate(convertDate(vector[82].trim()))
            .cutted(vector[83].trim())
            .eliminated(vector[84].trim())
            .replaced(vector[85].trim())
            .lastEntryCosts(Double.parseDouble(vector[86].trim().replaceAll(",", ".")))
            .returnedDate(convertDate(vector[87].trim()))
            .visitDay(vector[87].trim())
            .salesPromoter(vector[89].trim())
            .promoter2(vector[90].trim())
            .commercialAction(vector[91].trim())
            .polarizedStaggered(vector[92].trim())
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }

  private LocalDate convertDate(String date) {
    if (date.isEmpty()) {
      return null;
    }
    String[] splitString = date.split("/");
    if (splitString[0].isEmpty()) {
      return null;
    }
    String dateString = splitString[2] + "-" + splitString[1] + "-" + splitString[0];
    LocalDate dateConverted = LocalDate.parse(dateString);
    return dateConverted;
  }

  private Customer findCustomer(String customer) {
    if (customer.isEmpty()) {
      return null;
    }
    String[] splitCustomer = customer.split("-");
    Optional<Customer> customerResponse = repositoryCustomer.findByCode(splitCustomer[0]);
    if (!customerResponse.isPresent()) {
      return null;
    }
    return customerResponse.get();
  }

  private Teams findSeller(String seller) {
    if (seller.isEmpty()) {
      return null;
    }
    String[] splitSeller = seller.split("-");
    Optional<Teams> sellerResponse = repositoryTeams.findByMemberCode(splitSeller[0]);
    if (!sellerResponse.isPresent()) {
      return null;
    }
    return sellerResponse.get();
  }

  private ClusterGec findCluster(String cluster) {
    if (cluster.isEmpty()) {
      return null;
    }
    String[] splitCluster = cluster.split("-");
    Optional<ClusterGec> clusterResponse = repositoryCluster.findByClusterGec(splitCluster[1]);
    if (!clusterResponse.isPresent()) {
      return null;
    }
    return clusterResponse.get();
  }

  private SubChannel findSubChannel(String sub) {
    if (sub.isEmpty()) {
      return null;
    }
    String[] splitSub = sub.split("-");
    Optional<SubChannel> subResponse = repositorySub.findSubChannelByCode(splitSub[0]);
    if (!subResponse.isPresent()) {
      return null;
    }
    return subResponse.get();
  }

  private Products findProductByCode(String prod) {
    if (prod.isEmpty()) {
      return null;
    }
    String[] splitProd = prod.split("-");
    Products product = Products.builder().code(splitProd[0]).sku("sku-" + splitProd[0]).productDescription(splitProd[1])
        .active(true).build();
    Optional<Products> responseProd = repositoryProduct.findByCode(splitProd[0]);
    Products test = responseProd.get();
    if (!responseProd.isPresent()) {
      return repositoryProduct.save(product);
    }

    return responseProd.get();
  }

}
