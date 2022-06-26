package br.com.myanalista.controllers;

import br.com.myanalista.configs.PlaceToSaveFile;
import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.request.UploadFileRequest;
import br.com.myanalista.services.CustomerService;
import br.com.myanalista.services.SellOutService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/v1/upload", produces = {"application/json"})
@CrossOrigin("*")
@Slf4j
public class UploadFileDiary {

    @Autowired
    private PlaceToSaveFile saveFile;

    @Autowired
    private CustomerService serviceCustomer;

    @Autowired
    private SellOutService serviceSellout;

    @PostMapping
    public ResponseEntity<String> uploadFile(@ModelAttribute UploadFileRequest file) throws IOException, InterruptedException {

        try {
            String pathFileUpload = String.valueOf(saveFile.saveFile(file.getFile()));

            TimeUnit.SECONDS.sleep(2); // wait to startover new process

            if (pathFileUpload.equals("Error to upload file")) {
                return new ResponseEntity<>("{ \"message\": \"Error to upload file!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String fileNameOriginal = new String(Objects.requireNonNull(file.getFile().getOriginalFilename())) ;

            switch (fileNameOriginal.toLowerCase()) {
                case "cliente.csv":
                    serviceCustomer.recordDataToDb(file.getIdDistributor(), pathFileUpload);
                    break;
                case "sellout.csv":
                    serviceSellout.recordDataToDb(file.getIdDistributor(), pathFileUpload);
                    break;
                default:
                    return new ResponseEntity<>("{ \"message\": \"The name of file isn't standart\"}", HttpStatus.INTERNAL_SERVER_ERROR);
            }

//
            return new ResponseEntity<>("{ \"message\": \"Upload of file with success!\"}", HttpStatus.OK);
        } catch (BusinessException  e) {
            return new ResponseEntity<>("{ \"message\": \"Error to upload file!\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
