package com.example.finalrestprac.controllers;

import com.example.finalrestprac.services.FileService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/path")
    public String getFilePath(){
        return fileService.getStoragePath().toString();
    }

    @PostMapping("")
    public String saveFile(@RequestParam("file") @NotNull MultipartFile file){
        return fileService.saveFile(file);
    }

    @GetMapping("/{file}")
    public ResponseEntity<Resource> getFile(@PathVariable(name = "file") String fileName){
        String[] extension = fileName.split("\\.");
        switch (extension[extension.length - 1]){
            case "jpg" : return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileService.loadFile(fileName));
            case "png" : return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(fileService.loadFile(fileName));
            case "pdf" : return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(fileService.loadFile(fileName));
            default: throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unsupported File Type");
        }

    }
}
