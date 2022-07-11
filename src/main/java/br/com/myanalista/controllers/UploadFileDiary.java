package br.com.myanalista.controllers;

import br.com.myanalista.configs.PlaceToSaveFile;
import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.models.request.UploadFileRequest;
import br.com.myanalista.services.CustomerService;
import br.com.myanalista.services.EquipmentService;
import br.com.myanalista.services.LendingService;
import br.com.myanalista.services.SellOutService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Imports", description = "Gives daily load to the database")
public class UploadFileDiary {

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
                    return new ResponseEntity<>("{ \"message\": \"Error to upload file!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>("{ \"message\": \"Upload of file with success! \" }", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>("{ \"message\": \"Error to upload file!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
