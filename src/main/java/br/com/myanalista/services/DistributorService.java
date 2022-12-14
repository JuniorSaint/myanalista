package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.models.request.DistributorRequestPost;
import br.com.myanalista.models.request.DistributorRequestPut;
import br.com.myanalista.models.response.DistributorResponse;
import br.com.myanalista.models.response.DistributorSearchResponse;
import br.com.myanalista.repositories.DistributorRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistributorService {
    private DistributorRepository repository;
    private ModelMapper mapper;
    private Utils utils;

    @Transactional
    public ResponseEntity<DistributorResponse> save(DistributorRequestPost customerRequest) {
        Distributor distributorEntity = new Distributor();
        mapper.map(customerRequest, distributorEntity);
        Distributor customerCreated = repository.save(distributorEntity);
        DistributorResponse distributorResponse = new DistributorResponse();
        mapper.map(customerCreated, distributorResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(distributorResponse);
    }

    @Transactional
    public ResponseEntity<DistributorResponse> update(DistributorRequestPut contactRequest) {
        Distributor distributorEntity = new Distributor();
        mapper.map(contactRequest, distributorEntity);
        Distributor customerUpdate = repository.save(distributorEntity);
        DistributorResponse distributorResponse = new DistributorResponse();
        mapper.map(customerUpdate, distributorResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(distributorResponse);
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Optional<Distributor> contact = repository.findById(id);
        if (!contact.isPresent()) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted with success!");
    }

    public ResponseEntity<DistributorResponse> findById(Long id) {
        Optional<Distributor> distributor = repository.findById(id);
        if (distributor.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find customer with id: " + id);
        }
        DistributorResponse distributorResponse = new DistributorResponse();
        mapper.map(distributor.get(), distributorResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(distributorResponse);
    }

    public ResponseEntity<Distributor> findByIdEntity(Long id) {
        Optional<Distributor> distributor = repository.findById(id);
        if (distributor.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find customer with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(distributor.get());
    }

    public ResponseEntity<Page<DistributorSearchResponse>> findAllWithPage(Pageable pageable) {
        Page<Distributor> response = repository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapEntityPageIntoDtoPage(response, DistributorSearchResponse.class));
    }

    public ResponseEntity<Page<DistributorSearchResponse>> findAllWithPageSeek(String search, Pageable pageable) {
        Page<Distributor> response = repository.findByNickNameOrCompanyNameOrCnpjCpf(search, pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapEntityPageIntoDtoPage(response, DistributorSearchResponse.class));
    }

    public void recordDataToDb() throws IOException {

        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/DISTRIBUIDORAS.csv";

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

                Distributor channel = Distributor.builder()
                        .cnpjCpf(verifySizeOfCnpj(line.substring(0, index_1).trim()))
                        .companyType(line.substring(index_1 + 1, index_2).trim())
                        .companyName(line.substring(index_2 + 1, index_3).trim())
                        .fantasyName(line.substring(index_3 + 1, index_4).trim())
                        .nickName(line.substring(index_4 + 1, index_5).trim())
                        .city(line.substring(index_5 + 1, index_6).trim())
                        .state(line.substring(index_6 + 1).trim())
                        .build();

                repository.save(channel);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private String verifySizeOfCnpj(String cnpj) {
        if (cnpj.length() < 14) {
            return "0" + cnpj;
        }
        return cnpj;
    }

}
