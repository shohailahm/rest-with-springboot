package org.mvn.services;

import org.mvn.config.FileStorageConfig;
import org.mvn.exceptions.FileStorageException;
import org.mvn.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation= Paths.get(fileStorageConfig.getUploadDir());
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
          throw new FileStorageException("Could not create the directory",e);
        }
    }

    public String storeFile(MultipartFile file){
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(filename.contains("..")){
                throw new FileStorageException("Sorry conatins invalid name");
            }
            Path targetLocation=this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        }catch (Exception e){
             throw new MyFileNotFoundException("Could not store the file",e);

        }
    }

    public Resource loadFileAsResource(String filename){
        try {
            Path filePath=this.fileStorageLocation.resolve(filename).normalize();

           Resource resource=new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new MyFileNotFoundException("File Not Found"+filename);
            }
        } catch (Exception e) {
            throw new MyFileNotFoundException("File Not Found"+filename,e);
        }
    }
}
