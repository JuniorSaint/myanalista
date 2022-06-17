package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.repositories.EquipmentRepository;

@Service
public class EquipmentService {
  @Autowired
  private EquipmentRepository repository;

  public void recordDataToDb() throws IOException {  

    File myObj = new File("/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/06187813000119_EQUIPAMENTOS_RAF.TXT");
    
    try( Scanner myReader = new Scanner(myObj)) {

      while (myReader.hasNextLine()) {
        String line = myReader.nextLine();     

        Equipment channel = Equipment.builder()
            .patrimony(line.substring(6, 18).trim())
            .code(line.substring(21, 28).trim())
            .description(line.substring(29, 59).trim())
            .power(line.substring(60, 65).trim())
            .manufacturingDate(line.substring( 66, 75).trim())
            .serie(line.substring( 76, 91).trim())
            .prop(line.substring(92, 97).trim())
            .situation(line.substring(98, 109).trim())
            .doors(Integer.parseInt(line.substring(110, 115).trim()))
            .observation(line.substring(116, 136).trim())
            .build();

        repository.save(channel);
      }
    }catch(

  BusinessException e)
  {
    throw new BusinessException("Error to read file " + e.getMessage());
  }
}}
