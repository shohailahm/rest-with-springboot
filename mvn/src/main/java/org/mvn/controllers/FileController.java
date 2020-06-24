package org.mvn.controllers;

import io.swagger.annotations.Api;
import org.mvn.data.vo.UploadFileResponsevo;
import org.mvn.services.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@Api("File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileController {

    private static final Logger logger= LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponsevo uploadFile(@RequestParam("file") MultipartFile file){

        String fileStorage=fileStorageService.storeFile(file);

        String fileDownloadUri= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadFile").path(fileStorage).toUriString();
        return new UploadFileResponsevo(fileStorage,fileDownloadUri,file.getContentType(),file.getSize());

    }


    @PostMapping("/uploadMultipleFile")
    public List<UploadFileResponsevo> uploadMultipleFile(@RequestParam("files") MultipartFile[] files){

return Arrays.asList(files)
        .stream()
        .map(file->uploadFile(file))
        .collect(Collectors.toList());

    }

    @GetMapping("/downloadFile/{file:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("file") String file, HttpServletRequest request){
        Resource resource=fileStorageService.loadFileAsResource(file);
        String contentType=null;

        try {
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            logger.info("could not find extenstion of file");
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

}
