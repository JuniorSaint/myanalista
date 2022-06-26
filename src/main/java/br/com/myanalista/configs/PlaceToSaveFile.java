package br.com.myanalista.configs;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
@Slf4j
@Component
public class PlaceToSaveFile {

    public String saveFile(@RequestParam("file") MultipartFile file) {
        log.info("Receiving the file: ", file.getOriginalFilename());

        //To change name of file
        var path = CP.ROOT + UUID.randomUUID() + "." + extractExtensionOfFile(file.getOriginalFilename());

        log.info("New file name: " + path);

        try {
            Files.copy(file.getInputStream(), Path.of(path), StandardCopyOption.REPLACE_EXISTING);
         return path;
        } catch (Exception e) {
            log.error("Error to upload file", e);
           return "Error to upload file";
        }
    }
    private String extractExtensionOfFile(String fileName) {
        int i = fileName.lastIndexOf(".");
        return fileName.substring(i + 1);
    }
}
