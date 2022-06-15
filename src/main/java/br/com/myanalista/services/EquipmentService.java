package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.entities.Equipment;
import br.com.myanalista.repositories.EquipmentRepository;

@Service
public class EquipmentService {
  @Autowired
  private EquipmentRepository repository;

  public void recordDataToDb() throws IOException {

    String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/06187813000119_EQUIPAMENTOS_RAF.TXT";

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
        

        Equipment channel = Equipment.builder()
            .patrimony(line.substring(0, index_1))
            .code(line.substring(index_1 + 1, index_2).trim())
            .description(line.substring(index_1 + 1, index_2).trim())
            .power(line.substring(index_1 + 1, index_2).trim())
            // .manufacturingDate(line.substring( index_3 + 1, index_4).trim())
            .serie(line.substring( index_4 + 1, index_5).trim())
            .prop(line.substring(index_5 + 1, index_6).trim())
            .situation(line.substring(index_6 + 1).trim())
            .doors(Integer.parseInt(line.substring(index_6 + 1).trim()))
            .observation(line.substring(index_6 + 1).trim())
            .build();

        repository.save(channel);

        line = br.readLine();
      }
    } catch (IOException e) {
      throw new IOException("Error to read file " + e.getMessage());
    }
  }


}
