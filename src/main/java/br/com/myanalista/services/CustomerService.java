package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.exceptions.ErrorUploadFileException;
import br.com.myanalista.models.entities.*;
import br.com.myanalista.models.response.CriticizeFieldsResponse;
import br.com.myanalista.models.response.CustomerResponse;
import br.com.myanalista.models.response.FieldsCriticizedResponse;
import br.com.myanalista.repositories.*;
import lombok.AllArgsConstructor;
import org.joda.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository repository;
    private SubChannelRepository repositorySubChannel;
    private TeamsRepository repositoryTeams;
    private ModelMapper mapper;
    private ChannelRepository repositoryChannel;
    private DistributorRepository repositoryDistributor;
    private ClusterGecRepository repositoryCluster;
    private EmailService emailService;

    private Utils utils;

    public CustomerResponse findById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isEmpty()) {
            throw new EntityNotFoundException("There isn't customer with id: " + id);
        }
        return mapper.map(customer, CustomerResponse.class);
    }

    public Page<Customer> findAllWithPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ResponseEntity<CriticizeFieldsResponse> recordDataToDb(Long id, String path) throws IOException {
        CriticizeFieldsResponse sendEmailCriticize = new CriticizeFieldsResponse();
        Distributor distri = findDistributor(id);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            ArrayList<FieldsCriticizedResponse> criticizeArray = new ArrayList<FieldsCriticizedResponse>();
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

                if (line.substring(index_8 + 1, index_9).trim().equals("")) {
                    criticizeArray.add(FieldsCriticizedResponse.builder().field("Cidade").customerName(line.substring(index_4 + 1, index_5).trim()).build());
                }
                if (line.substring(index_13 + 1, index_14).trim() == "") {
                    criticizeArray.add(FieldsCriticizedResponse.builder().field("Subcanal").customerName(line.substring(index_4 + 1, index_5).trim()).build());
                }
                if (line.substring(index_14 + 1, index_15).trim().equals("")) {
                    criticizeArray.add(FieldsCriticizedResponse.builder().field("Semana").customerName(line.substring(index_4 + 1, index_5).trim()).build());
                }

                sendEmailCriticize = CriticizeFieldsResponse.builder()
                        .distributor(repositoryDistributor.findById(id).get().getCompanyName())
                        .cnpj(repositoryDistributor.findById(id).get().getCnpjCpf())
                        .criticizes(criticizeArray)
                        .build();

                Boolean isExist = ifCustomerExist(line.substring(0, index_1).trim(), distri);
                if (!isExist) {
                    Customer channel = Customer.builder()
                            .code(line.substring(0, index_1).trim())
                            .route(line.substring(index_1 + 1, index_2).trim())
                            .cnpj(line.substring(index_2 + 1, index_3).trim())
                            .inscrEstadual(line.substring(index_3 + 1, index_4).trim())
                            .companyName(line.substring(index_4 + 1, index_5).trim())
                            .fantasyName(line.substring(index_5 + 1, index_6).trim())
                            .address(line.substring(index_6 + 1, index_7).trim())
                            .number(line.substring(index_7 + 1, index_8).trim())
                            .city(line.substring(index_8 + 1, index_9).trim())
                            .state(line.substring(index_9 + 1, index_10).trim())
                            .zipCode(line.substring(index_10 + 1, index_11).trim())
                            .district(line.substring(index_11 + 1, index_12).trim())
                            .phoneNumber(line.substring(index_12 + 1, index_13).trim())
                            .subChannel(findSubChannelBySubChannel(line.substring(index_13 + 1, index_14).trim()))
                            .week(line.substring(index_14 + 1, index_15).trim())
                            .sequence(line.substring(index_15 + 1, index_16).trim())
                            .email(line.substring(index_16 + 1, index_17).trim())
                            .tablePrice(line.substring(index_17 + 1, index_18).trim())
                            .groupBusiness(line.substring(index_18 + 1, index_19).trim())
                            .seller(findTeamsMemberByCode(line.substring(index_19 + 1, index_20).trim(), distri))
                            .supervisor(line.substring(index_20 + 1, index_21).trim())
                            .area(line.substring(index_21 + 1, index_22).trim())
                            .originalPaymentMethod(line.substring(index_22 + 1, index_23).trim())
                            .maximunDays(line.substring(index_23 + 1, index_24).trim())
                            .turnover(line.substring(index_24 + 1, index_25).trim())
                            .regiterDay(line.substring(index_25 + 1, index_26).trim())
                            .inactivationDay(line.substring(index_26 + 1, index_27).trim())
                            .status(line.substring(index_27 + 1, index_28).trim())
                            .clusterGec(findClusterByName(line.substring(index_28 + 1, index_29).trim()))
                            .refPet(line.substring(index_29 + 1, index_30).trim())
                            .ls(line.substring(index_30 + 1, index_31).trim())
                            .rgb(line.substring(index_31 + 1, index_32).trim())
                            .lastPurchase(line.substring(index_32 + 1, index_33).trim())
                            .creditLimit(line.substring(index_33 + 1, index_34).trim())
                            .addition(line.substring(index_34 + 1, index_35))
                            .sellerCustomer2(findTeamsMemberByCode(line.substring(index_35 + 1, index_36).trim(), distri))
                            .week2(line.substring(index_36 + 1, index_37).trim())
                            .turnover2(line.substring(index_37 + 1, index_38).trim())
                            .distributor(distri)
                            .latitude(line.substring(index_39 + 1, index_40).trim())
                            .longitude(line.substring(index_40 + 1, index_41).trim())
                            .notAllowCurrentRestChange(line.substring(index_41 + 1, index_42).trim())
                            .notChargeTicketBillingFee(line.substring(index_42 + 1, index_43).trim())
                            .cidCode(line.substring(index_43 + 1, index_44).trim())
                            .currentPaymentMethod(line.substring(index_44 + 1, index_45).trim())
                            .coverage(line.substring(index_45 + 1, index_46).trim())
                            .phoneNumber2(line.substring(index_46 + 1, index_47).trim())
                            .phoneNumber3(line.substring(index_47 + 1, index_48).trim())
                            .phoneNumber4(line.substring(index_48 + 1, index_49).trim())
                            .promoter(line.substring(index_49 + 1, index_50).trim())
                            .promoterEq2(line.substring(index_50 + 1, index_51).trim())
                            .channel(findChannel(line.substring(index_51 + 1, index_52).trim()))
                            .specie(line.substring(index_52 + 1))
                            .build();

                    repository.save(channel);

                    line = br.readLine();
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            throw new ErrorUploadFileException(
                    "Could not store file. Please try again!, " + e);
        }
        String pattern = " dd 'de' MMMM 'de' YYYY ";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        SenderEmail senderEmail = SenderEmail.builder()
                .emailTo("junior.garbage@gmail.com")
                .emailFrom("contato@idip.com.br")
                .subject("Relatório de carga do cliente: " + distri.getCompanyName())
                .text(sdf.format(new LocalDate()) +
                        "Abaixo Relação de observações encontradas no importe do dia"
                        + "da distribuidora: " + distri.getNickName() + sendEmailCriticize)
                .build();
        emailService.sendEmail(senderEmail);
        return ResponseEntity.status(HttpStatus.OK).body(sendEmailCriticize);
    }

    private boolean ifCustomerExist(String code, Distributor distributor) {
        if (code.isEmpty() || distributor == null) {
            return true;
        }
        Optional<Customer> response = repository.findCustomerByCodeAndDistributor(code.trim(), distributor);
        if (response.isPresent()) {
            return true;
        }
        return false;
    }

    private ClusterGec findClusterByName(String name) {
        if (name.isEmpty()) {
            return null;
        }
        Optional<ClusterGec> clusterGec = repositoryCluster.findByClusterGec(name);
        if (clusterGec.isEmpty()) {
            return null;
        }
        return clusterGec.get();
    }

    private Distributor findDistributor(Long id) {
        Optional<Distributor> response = repositoryDistributor.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        return null;
    }


    private SubChannel findSubChannelBySubChannel(String name) {
        try {
            Optional<SubChannel> response = repositorySubChannel.findSubChannelBysubChannel(name.trim());
            if (response.isPresent()) {
                return response.get();
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
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

        Optional<Teams> responseTeams = repositoryTeams.findByMemberCodeAndDistributor(code, distributor);
        if (responseTeams.isEmpty()) {
            Teams teamsBuild = Teams.builder().memberCode(code).fullName(nameSaller).distributor(distributor).build();
            return repositoryTeams.save(teamsBuild);
        }
        return responseTeams.get();
    }

    public CustomerResponse findCustomerByCode(String code) {
        if (code.isEmpty()) {
            return null;
        }
        Optional<Customer> response = repository.findByCode(code);
        if (response.isEmpty()) {
            throw new EntityNotFoundException("There isn't customer with code: " + code);
        }
        CustomerResponse customer = new CustomerResponse();
        mapper.map(response.get(), customer);
        return customer;
    }

    private Channel findChannel(String channel) {
        if (channel.isEmpty()) {
            return null;
        }
        Optional<Channel> responseChannel = repositoryChannel.findChannelBychannel(channel);
        if (!responseChannel.isPresent()) {
            return null;
        }
        return responseChannel.get();
    }


}
