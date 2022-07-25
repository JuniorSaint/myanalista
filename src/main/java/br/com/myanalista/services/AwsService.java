package br.com.myanalista.services;


import br.com.myanalista.configs.CP;
import br.com.myanalista.exceptions.ErrorUploadFileException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AwsService {
    private AmazonS3Client awsS3Client;

    public String uploadFile(MultipartFile file) {
        //To change name of file
        var path =  file.getOriginalFilename();
//        var path = LocalDateTime.now() + "_" + file.getOriginalFilename();

        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentLength(file.getSize());
        metaData.setContentType(file.getContentType());

        try {
            awsS3Client.putObject(CP.BUCKET, path, file.getInputStream(), metaData);
        } catch (IOException e) {
            throw new ErrorUploadFileException("Error occurred while the file uploading");
        }

        awsS3Client.setObjectAcl(CP.BUCKET, path, CannedAccessControlList.PublicRead);

        return awsS3Client.getResourceUrl(CP.BUCKET, path);
    }
}