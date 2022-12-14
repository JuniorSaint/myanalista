package br.com.myanalista.services;

import br.com.myanalista.exceptions.ErrorUploadFileException;
import br.com.myanalista.models.entities.*;
import br.com.myanalista.repositories.*;
import io.jsonwebtoken.io.IOException;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LendingService {
    private LendingRepository repository;
    private CustomerRepository repositoryCustomer;
    private SubChannelRepository repositorySub;
    private EquipmentRepository repositoryEquipment;
    private TeamsRepository repositoryTeams;
    private DistributorRepository repositoryDistributor;
    private ClusterGecRepository repositoryCluster;

    public ResponseEntity<Lending> findById(Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findById(id).get());
    }

    public ResponseEntity<Page<Lending>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(repository.findAll(pageable));
    }

    public void recordDataToDb(Long id, String path) throws IOException {
        try {
            @Cleanup FileInputStream file = new FileInputStream(new File(path));  // @cleanup is to void close the file in the end
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);  // Set wich sheet the file is reading
            sheet.removeRow(sheet.getRow(0)); // remove rows of header.
            sheet.removeRow(sheet.getRow(1));
            sheet.removeRow(sheet.getRow(2));
            List<Row> rows = (List<Row>) toList(sheet.iterator());  // Iterate the rows

            rows.forEach(row -> {
                List<Cell> cells = (List<Cell>) toList(row.cellIterator());  // Iterate the cells

                Lending lending = Lending.builder()
                        .territory(cells.get(0).getStringCellValue())
                        .distributor(distributor(id))
                        .customerRegistration(findCustomer((int) cells.get(2).getNumericCellValue(), distributor(id)))
                        .cluster(findCluster(cells.get(7).getStringCellValue()))
                        .subChannel(findSubChannel(cells.get(8).getStringCellValue()))
                        .city(cells.get(9).getStringCellValue())
                        .equipmentNumber(findEquipment(cells.get(10), distributor(id)))
                        .contract((int) cells.get(14).getNumericCellValue())
                        .amount((int) cells.get(17).getNumericCellValue())
                        .dateSend(verifyDateIsValid(cells.get(18)))
                        .dueDate(verifyDateIsValid(cells.get(19)))
                        .sellerCode(findSeller((int) cells.get(20).getNumericCellValue(), distributor(id)))
                        .route((int) cells.get(21).getNumericCellValue())
                        .nfe((int) cells.get(22).getNumericCellValue())
                        .conservation(cells.get(23).getStringCellValue())
                        .build();
                repository.save(lending);
            });
        } catch (Exception e) {
            throw new ErrorUploadFileException(
                    "Could not store file. Please try again!, " + e);
        }
    }

    private Equipment findEquipment(Cell code, Distributor distributor) {
        String newCode = code.toString().replace(".0", "");
        if (newCode.isEmpty()) {
            return null;
        }
        Optional<Equipment> responseEquipment = repositoryEquipment.findByPatrimonyAndDistributor(newCode, distributor);
        if (responseEquipment.isEmpty()) {
            return null;
        }
        return responseEquipment.get();
    }

    private LocalDateTime verifyDateIsValid(Cell date) {
        try {
            return date.getLocalDateTimeCellValue();
        } catch (IllegalStateException | NumberFormatException c) {
            return null;
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