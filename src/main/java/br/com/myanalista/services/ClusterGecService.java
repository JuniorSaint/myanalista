package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.models.entities.ClusterGec;
import br.com.myanalista.models.response.ClusterResponse;
import br.com.myanalista.repositories.ClusterGecRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
@AllArgsConstructor
public class ClusterGecService {
    private ClusterGecRepository repository;

    private ModelMapper mapper;

    private Utils utils;

    public Page<ClusterResponse> findAllWithPage(Pageable pageable) {
        Page<ClusterGec> responses = repository.findAll(pageable);
        return utils.mapEntityPageIntoDtoPage(responses, ClusterResponse.class);
    }

    public void recordDataToDb() throws IOException {

        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/GEC.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();
            while (line != null) {

                int index_1 = line.indexOf(",");

                ClusterGec channel = ClusterGec.builder()
                        .clusterGec(line.substring(0, index_1))
                        .gecIne(line.substring(index_1 + 1))
                        .build();

                repository.save(channel);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

}
