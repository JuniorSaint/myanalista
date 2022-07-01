package br.com.myanalista.services;

import br.com.myanalista.models.entities.*;
import br.com.myanalista.repositories.*;
import io.jsonwebtoken.io.IOException;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ClusterGecRepository repositoryCluster;

    public void recordDataToDb(Long id, String path) throws IOException {
        try {
            @Cleanup FileInputStream file = new FileInputStream(new File(path));  // @cleanup is to void close the file in the end
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);  // Set wich sheet the file is reading

            List<Row> rows = (List<Row>) toList(sheet.iterator());  // Iterate the rows

            rows.remove(0); // remove the line to read

            rows.forEach(row -> {

                List<Cell> cells = (List<Cell>) toList(row.cellIterator());  // Iterate the cells

                Lending lending = Lending.builder()
                        .territory(cells.get(0).getStringCellValue())
                        .distributor(distributor(id))
                        .customerRegistration(findCustomer((int) cells.get(2).getNumericCellValue(), distributor(id)))
                        .cluster(findCluster(cells.get(7).getStringCellValue()))
                        .subChannel(findSubChannel(cells.get(8).getStringCellValue()))
                        .city(cells.get(9).getStringCellValue())
                        .equipmentNumber(findEquipment(cells.get(10).getStringCellValue(), distributor(id)))
                        .contract((int) cells.get(14).getNumericCellValue())
                        .amount((int) cells.get(17).getNumericCellValue())
                        .dateSend((cells.get(18).getDateCellValue()))
                        .dueDate((cells.get(19).getDateCellValue()))
                        .sellerCode(findSeller((int) cells.get(19).getNumericCellValue(), distributor(id)))
                        .route((int) cells.get(21).getNumericCellValue())
                        .nfe((int) cells.get(22).getNumericCellValue())
                        .conservation(cells.get(23).getStringCellValue())
                        .build();
                repository.save(lending);
            });
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<?> toList(Iterator<?> iterator) {
        return IteratorUtils.toList(iterator);
    }

    private Distributor distributor(Long id) {
        Optional<Distributor> dis = repositoryDistributor.findById(id);
        return dis.get();
    }

    private Customer findCustomer(Integer code, Distributor distributor) {
        String newCode = code.toString();
        if (newCode.isEmpty()) {
            return null;
        }
        String zeros = "";
        for (int x = 0; x < (5 - newCode.length()); x++) {
            zeros += "0";
        }
        String codeResult = zeros + code;
        Optional<Customer> customerResponse = repositoryCustomer.findCustomerByCodeAndDistributor(codeResult, distributor);
        if (!customerResponse.isPresent()) {
            return null;
        }
        return customerResponse.get();
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

    private ClusterGec findCluster(String name) {
        if (name.isEmpty()) {
            return null;
        }
        Optional<ClusterGec> clusterGec = repositoryCluster.findByClusterGec(name);
        if (clusterGec.isEmpty()) {
            return null;
        }
        return clusterGec.get();
    }

    private Equipment findEquipment(String code, Distributor distributor) {
        String newCode = code.toString();
        if (newCode.isEmpty()) {
            return null;
        }
        Optional<Equipment> responseEquipment = repositoryEquipment.findByPatrimonyAndDistributor(newCode, distributor);
        if (responseEquipment.isEmpty()) {
            return null;
        }
        return responseEquipment.get();
    }

    private Teams findSeller(Integer code, Distributor distributor) {
        String newCode = code.toString();
        if (newCode.isEmpty()) {
            return null;
        }
        String zeros = "";
        for (int x = 0; x < (3 - newCode.length()); x++) {
            zeros += "0";
        }
        String codeResult = zeros + newCode;
        Optional<Teams> sellerResponse = repositoryTeams.findByMemberCodeAndDistributor(codeResult, distributor);
        if (!sellerResponse.isPresent()) {
            return null;
        }
        return sellerResponse.get();
    }
}