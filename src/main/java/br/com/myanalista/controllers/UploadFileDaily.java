package br.com.myanalista.controllers;

import br.com.myanalista.configs.PlaceToSaveFile;
import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.exceptions.ErrorUploadFileException;
import br.com.myanalista.models.request.UploadFileRequest;
import br.com.myanalista.services.CustomerService;
import br.com.myanalista.services.EquipmentService;
import br.com.myanalista.services.LendingService;
import br.com.myanalista.services.SellOutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/v1/upload", produces = {"application/json"})
@CrossOrigin("*")
@Slf4j
@AllArgsConstructor
public class UploadFileDaily {

    @Autowired
    private PlaceToSaveFile saveFile;
    @Autowired
    private CustomerService serviceCustomer;
    @Autowired
    private SellOutService serviceSellout;
    @Autowired
    private EquipmentService serviceEquipment;
    @Autowired
    private LendingService serviceLending;
    @PostMapping
    public ResponseEntity<String> uploadFile(@ModelAttribute UploadFileRequest files) throws IOException, InterruptedException {
        try {
            for (MultipartFile uploadedFile : files.getFile()) {
                String pathFileUpload = String.valueOf(saveFile.saveFile(uploadedFile));

                if (pathFileUpload.equals("Error to upload file")) {
                    throw new ErrorUploadFileException("There was a problem to upload file try again or call the administrator");
                }

                String fileNameOriginal = new String(Objects.requireNonNull(uploadedFile.getOriginalFilename()));

                if (fileNameOriginal.toLowerCase().contains("cliente")) {
                    serviceCustomer.recordDataToDb(files.getIdDistributor(), pathFileUpload);
                }

                if (fileNameOriginal.toLowerCase().contains("sellout")) {
                    serviceSellout.recordDataToDb(files.getIdDistributor(), pathFileUpload);
                }
                if (fileNameOriginal.toLowerCase().contains("equipamentos")) {
                    serviceEquipment.recordDataToDb(files.getIdDistributor(), pathFileUpload);
                }
                if (fileNameOriginal.toLowerCase().contains("comodatos")) {
                    serviceLending.recordDataToDb(files.getIdDistributor(), pathFileUpload);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("Upload of the file was successful");
        } catch (BadRequestException e) {
            throw new ErrorUploadFileException("There was a problem to upload file try again or call the administrator");
        }
    }
}
