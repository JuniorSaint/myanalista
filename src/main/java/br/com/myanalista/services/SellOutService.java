package br.com.myanalista.services;

import br.com.myanalista.exceptions.ErrorUploadFileException;
import br.com.myanalista.models.entities.*;
import br.com.myanalista.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

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
    private DistributorRepository repositoryDistributor;
    @Autowired
    private ProductRepository repositoryProduct;

    public void recordDataToDb(Long id, String path) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();
            while (line != null) {

                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);
                int index_3 = line.indexOf(";", index_2 + 1);
                int index_4 = line.indexOf(";", index_3 + 1);
                int index_5 = line.indexOf(";", index_4 + 1);
                int index_6 = line.indexOf(";", index_5 + 1);
                int index_7 = line.indexOf(";", index_6 + 1);
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
                int index_25 = line.indexOf(";", index_24 + 1);
                int index_26 = line.indexOf(";", index_25 + 1);
                int index_27 = line.indexOf(";", index_26 + 1);
                int index_28 = line.indexOf(";", index_27 + 1);
                int index_29 = line.indexOf(";", index_28 + 1);
                int index_30 = line.indexOf(";", index_29 + 1);
                int index_31 = line.indexOf(";", index_30 + 1);
                int index_32 = line.indexOf(";", index_31 + 1);
                int index_33 = line.indexOf(";", index_32 + 1);
                int index_34 = line.indexOf(";", index_33 + 1);
                int index_35 = line.indexOf(";", index_34 + 1);
                int index_36 = line.indexOf(";", index_35 + 1);
                int index_37 = line.indexOf(";", index_36 + 1);
                int index_38 = line.indexOf(";", index_37 + 1);
                int index_39 = line.indexOf(";", index_38 + 1);
                int index_40 = line.indexOf(";", index_39 + 1);
                int index_41 = line.indexOf(";", index_40 + 1);
                int index_42 = line.indexOf(";", index_41 + 1);
                int index_43 = line.indexOf(";", index_42 + 1);
                int index_44 = line.indexOf(";", index_43 + 1);
                int index_45 = line.indexOf(";", index_44 + 1);
                int index_46 = line.indexOf(";", index_45 + 1);
                int index_47 = line.indexOf(";", index_46 + 1);
                int index_48 = line.indexOf(";", index_47 + 1);
                int index_49 = line.indexOf(";", index_48 + 1);
                int index_50 = line.indexOf(";", index_49 + 1);
                int index_51 = line.indexOf(";", index_50 + 1);
                int index_52 = line.indexOf(";", index_51 + 1);
                int index_53 = line.indexOf(";", index_52 + 1);
                int index_54 = line.indexOf(";", index_53 + 1);
                int index_55 = line.indexOf(";", index_54 + 1);
                int index_56 = line.indexOf(";", index_55 + 1);
                int index_57 = line.indexOf(";", index_56 + 1);
                int index_58 = line.indexOf(";", index_57 + 1);
                int index_59 = line.indexOf(";", index_58 + 1);
                int index_60 = line.indexOf(";", index_59 + 1);
                int index_61 = line.indexOf(";", index_60 + 1);
                int index_62 = line.indexOf(";", index_61 + 1);
                int index_63 = line.indexOf(";", index_62 + 1);
                int index_64 = line.indexOf(";", index_63 + 1);
                int index_65 = line.indexOf(";", index_64 + 1);
                int index_66 = line.indexOf(";", index_65 + 1);
                int index_67 = line.indexOf(";", index_66 + 1);
                int index_68 = line.indexOf(";", index_67 + 1);
                int index_69 = line.indexOf(";", index_68 + 1);
                int index_70 = line.indexOf(";", index_69 + 1);
                int index_71 = line.indexOf(";", index_70 + 1);
                int index_72 = line.indexOf(";", index_71 + 1);
                int index_73 = line.indexOf(";", index_72 + 1);
                int index_74 = line.indexOf(";", index_73 + 1);
                int index_75 = line.indexOf(";", index_74 + 1);
                int index_76 = line.indexOf(";", index_75 + 1);
                int index_77 = line.indexOf(";", index_76 + 1);
                int index_78 = line.indexOf(";", index_77 + 1);
                int index_79 = line.indexOf(";", index_78 + 1);
                int index_80 = line.indexOf(";", index_79 + 1);
                int index_81 = line.indexOf(";", index_80 + 1);
                int index_82 = line.indexOf(";", index_81 + 1);
                int index_83 = line.indexOf(";", index_82 + 1);
                int index_84 = line.indexOf(";", index_83 + 1);
                int index_85 = line.indexOf(";", index_84 + 1);
                int index_86 = line.indexOf(";", index_85 + 1);
                int index_87 = line.indexOf(";", index_86 + 1);
                int index_88 = line.indexOf(";", index_87 + 1);
                int index_89 = line.indexOf(";", index_88 + 1);
                int index_90 = line.indexOf(";", index_89 + 1);
                int index_91 = line.indexOf(";", index_90 + 1);
                int index_92 = line.indexOf(";", index_91 + 1);
                Distributor distri = findDistributor(id);

                SellOut channel = SellOut.builder()
                        .distributor(distri)
                        .date(convertDate(line.substring(index_1 + 1, index_2).trim()))
                        .customer(findCustomer(line.substring(index_2 + 1, index_3).trim(), distri))
                        .route(line.substring(index_3 + 1, index_4).trim())
                        .sellersOrder(findTeamsMemberByCode(line.substring(index_4 + 1, index_5).trim(), distri))
                        .supervisorsOrder(line.substring(index_5 + 1, index_6).trim())
                        .sellerRegistration(findTeamsMemberByCode(line.substring(index_6 + 1, index_7).trim(), distri))
                        .supervisorRegistration(line.substring(index_7 + 1, index_8).trim())
//                        .city(line.substring(index_8 + 1, index_9).trim().trim())
                        .typeOperation(line.substring(index_9 + 1, index_10).trim())
                        .nfNumber(line.substring(index_10 + 1, index_11))
                        .product(findProductByCode(line.substring(index_33 + 1, index_34).trim()))
                        .amount(Double.parseDouble(line.substring(index_12 + 1, index_13).trim().replaceAll(",", ".")))
                        .liter(line.substring(index_13 + 1, index_14).trim().replace(",", "."))
                        .physicalBox(line.substring(index_14 + 1, index_15).trim())
                        .condition(line.substring(index_15 + 1, index_16).trim())
                        .weight(Double.parseDouble(line.substring(index_16 + 1, index_17).trim().replaceAll(",", ".")))
                        .priceSell(Double.parseDouble(line.substring(index_17 + 1, index_18).trim().replaceAll(",", ".")))
                        .priceCost(String.format("%.3f",
                                Double.parseDouble(line.substring(index_18 + 1, index_19).trim().replaceAll(",", "."))))
                        .tablePrice(Double.parseDouble(line.substring(index_19 + 1, index_20).trim().replace(",", ".")))
                        .groupR(line.substring(index_20 + 1, index_21).trim()) // product's group, don't put it()
//                        .category(line.substring(index_21 + 1, index_22).trim())
                        .brand(line.substring(index_22 + 1, index_23).trim())
                        // .tableSell(Integer.parseInt(line.substring(index_23 + 1, index_24).trim()))
                        .cluster(findCluster(line.substring(index_24 + 1, index_25).trim()))
                        .channel(findSubChannel(line.substring(index_25 + 1, index_26).trim()))
                        .averageTerm(line.substring(index_26 + 1, index_27).trim().isEmpty() ? 0
                                : Integer.parseInt(line.substring(index_26 + 1, index_27).trim()))
                        .cfop(line.substring(index_27 + 1, index_28).trim())
//                        .fantasy(line.substring(index_28 + 1, index_29).trim())
                        .physicalJuridical(line.substring(index_29 + 1, index_30).trim())
                        .packages(line.substring(index_30 + 1, index_31).trim())
                        .ean(line.substring(index_31 + 1, index_32).trim())
                        .ncm(line.substring(index_32 + 1, index_33).trim())
                        .cia(line.substring(index_33 + 1, index_34).trim())
                        .unit(line.substring(index_34 + 1, index_35).trim())
                        .monthYear(line.substring(index_35 + 1, index_36).trim())
                        .comission(line.substring(index_36 + 1, index_37).trim().replace(",", "."))
//                        .district(line.substring(index_37 + 1, index_38).trim())
                        .sellPrice(String.format("%.3f",
                                Double.parseDouble(line.substring(index_38 + 1, index_39).trim().replaceAll(",", "."))))
                        .amountReturned(Double.parseDouble(line.substring(index_39 + 1, index_40).trim().replaceAll(",", ".")))
                        .literReturned(Double.parseDouble(line.substring(index_40 + 1, index_41).trim().replaceAll(",", ".")))
                        .physicalBoxReturned(line.substring(index_41 + 1, index_42).trim().isEmpty() ? 0
                                : Integer.parseInt(line.substring(index_45 + 1, index_46).trim().replace(",", ".")))
                        .valueReturned(Double.parseDouble(line.substring(index_42 + 1, index_43).trim().replaceAll(",", ".")))
                        .valueMeta(Double.parseDouble(line.substring(index_43 + 1, index_44).trim()))
                        .amountMeta(Integer.parseInt(line.substring(index_44 + 1, index_45).trim()))
                        .physicalBoxMeta(line.substring(index_45 + 1, index_46).trim().isEmpty() ? 0
                                : Integer.parseInt(line.substring(index_45 + 1, index_46).trim().replace(",", ".")))
                        .coverMeta(line.substring(index_46 + 1, index_47).trim())
                        .averageTermMeta(line.substring(index_47 + 1, index_48).trim())
                        .seller2(findTeamsMemberByCode(line.substring(index_48 + 1, index_49).trim(), distri))
                        .supervisor2(line.substring(index_49 + 1, index_50).trim())
                        .route2(line.substring(index_50 + 1, index_51).trim())
                        .quarter(line.substring(index_51 + 1, index_52).trim())
                        .orderDate(convertDate(line.substring(index_52 + 1, index_53).trim()))
                        .unitBox(String.format("%.3f",
                                Double.parseDouble(line.substring(index_53 + 1, index_54).trim().replace(",", "."))))
                        .unitBoxReturned(String.format("%.3f",
                                Double.parseDouble(line.substring(index_54 + 1, index_55).trim().replace(",", "."))))
                        .unitBoxBox(String.format("%.3f",
                                Double.parseDouble(line.substring(index_55 + 1, index_56).trim().replace(",", "."))))
                        .unitBoxRmeta(String.format("%.3f",
                                Double.parseDouble(line.substring(index_56 + 1, index_57).trim().replace(",", "."))))
//                        .register(findCustomer(line.substring(index_57 + 1, index_58).trim().replaceAll("[^0-9]", "")))
                        .area(line.substring(index_58 + 1, index_59).trim())
                        .discountCustomer((line.substring(index_59 + 1, index_60).trim()).isEmpty() ? 0.00
                                : Double.parseDouble(line.substring(index_59 + 1, index_60).trim().replace(",", ".")))
//                        .uf(line.substring(index_60 + 1, index_61).trim())
                        .toR(line.substring(index_61 + 1, index_62).trim())
                        .map(line.substring(index_62 + 1, index_63).trim())
//                        .address(line.substring(index_63 + 1, index_64).trim())
                        .motiveReturn(line.substring(index_64 + 1, index_65).trim())
                        .productType(line.substring(index_65 + 1, index_66).trim())
                        .transaction(Integer.parseInt(line.substring(index_66 + 1, index_67).trim()))
                        .consumptionType(line.substring(index_67 + 1, index_68).trim())
                        .year(line.substring(index_68 + 1, index_69).trim())
                        .active(line.substring(index_69 + 1, index_70).trim())
                        .purchase(line.substring(index_70 + 1, index_71).trim())
                        .equipment(line.substring(index_71 + 1, index_72).trim())
                        .gvSup(line.substring(index_72 + 1, index_73).trim())
                        .adfFin(Double.parseDouble(line.substring(index_73 + 1, index_74).trim().replace(",", ".")))
                        .cobBol(Double.parseDouble(line.substring(index_74 + 1, index_75).trim().replace(",", ".")))
                        .companyCategory(line.substring(index_75 + 1, index_76).trim())
                        .companySubCategory(line.substring(index_76 + 1, index_77).trim())
                        .companyReturnability(line.substring(index_77 + 1, index_78).trim())
                        .companyBrand(line.substring(index_78 + 1, index_79).trim())
                        .flavor(line.substring(index_79 + 1, index_80).trim())
                        .segment(line.substring(index_80 + 1, index_81).trim())
                        .consumptionOccasion(line.substring(index_81 + 1, index_82).trim())
                        .nfeDate(convertDate(line.substring(index_82 + 1, index_83).trim()))
                        .cutted(line.substring(index_83 + 1, index_84).trim().replace(",", "."))
                        .eliminated(line.substring(index_84 + 1, index_85).trim().replace(",", "."))
                        .replaced(line.substring(index_85 + 1, index_86).trim())
                        .lastEntryCosts(Double.parseDouble(line.substring(index_86 + 1, index_87).trim().replaceAll(",", ".")))
                        .returnedDate(convertDate(line.substring(index_87 + 1, index_88).trim()))
                        .visitDay(line.substring(index_88 + 1, index_89).trim())
                        .salesPromoter(line.substring(index_89 + 1, index_90).trim())
                        .promoter2(line.substring(index_90 + 1, index_91).trim())
                        .commercialAction(line.substring(index_91 + 1, index_92).trim())
                        .polarizedStaggered(line.substring(index_92 + 1).trim())
                        .build();

                repository.save(channel);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new ErrorUploadFileException(
                    "Could not store file. Please try again!, " + e);
        }
    }

    private Distributor findDistributor(Long id) {
        if (id == null) {
            return null;
        }
        Optional<Distributor> response = repositoryDistributor.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        return null;
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

    private Customer findCustomer(String code, Distributor distributor) {
        if (code.isEmpty() || distributor == null) {
            return null;
        }
        String responseCode = code.split("-")[0];
        Optional<Customer> customerResponse = repositoryCustomer.findCustomerByCodeAndDistributor(responseCode, distributor);
        if (!customerResponse.isPresent()) {
            return null;
        }
        return customerResponse.get();
    }

    private Teams findTeamsMemberByCode(String codeOrigin, Distributor distributor) {
        if (codeOrigin.isEmpty() || distributor == null) {
            return null;
        }

        String[] nameCode = codeOrigin.split("-");
        if (codeOrigin.toString().trim().equals("N/D")) {
            return null;
        }
        String code = nameCode[0];
        String nameSaller = nameCode[1].trim();

        String newCodeMember = code;
        for (Integer x = 0; x < (3 - code.length()); x++) {
            newCodeMember = "0".concat(newCodeMember);
        }

        Optional<Teams> responseTeams = repositoryTeams.findByMemberCodeAndDistributor(newCodeMember, distributor );
        if (responseTeams.isEmpty()) {
            Teams teamsBuild = Teams.builder().memberCode(code).fullName(nameSaller).distributor(distributor).build();
            return repositoryTeams.save(teamsBuild);
        }
        return responseTeams.get();
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

    private Products findProductByCode(String sku) {
        if(sku.trim().isEmpty()){
            Optional<Products> responseProd = repositoryProduct.findByCodeSku(900000);
            return responseProd.get();
        }
        Optional<Products> responseProd = repositoryProduct.findByCodeSku(Integer.parseInt(sku));
        if (!responseProd.isPresent()) {
            return null;
        }
        return responseProd.get();
    }

}
