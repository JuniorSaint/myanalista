package br.com.myanalista.services;


import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import br.com.myanalista.models.entities.Distributor;
import br.com.myanalista.repositories.DistributorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.repositories.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository repository;

    @Autowired
    private DistributorRepository repositoryDistributor;

    public void recordDataToDb(Long id, String path) throws IOException {

        File myObj = new File(path);
        try (Scanner myReader = new Scanner(myObj)) {

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                if (line.length() > 85 && !line.substring(4, 18).trim().equals("PATRIMONIO")) {
                        Equipment channel = Equipment.builder()
                                .patrimony(line.substring(4, 18).trim())
                                .code(line.substring(21, 27).trim())
                                .description(line.substring(27, 59).trim())
                                .power(line.substring(58, 65).trim())
                                .manufacturingDate(line.substring(65, 75).trim())
                                .serie(line.substring(75, 91).trim())
                                .prop(line.substring(90, 97).trim())
                                .situation(line.substring(97, 107).trim())
                                .doors(Integer.parseInt(line.length() > 108 ? line.substring(110, 113).trim() : "0"))
                                .observation(line.length() > 114 ? line.substring(116, line.length()).trim() : null)
                                .distributor(findDistributorById(id))
                                .build();
                        repository.save(channel);
                }
            }
        } catch (BusinessException e) {
            throw new BusinessException("Error to read file " + e.getMessage());
        }
    }

    private Distributor findDistributorById(Long id) {
        Optional<Distributor> response = repositoryDistributor.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        return null;
    }
}
