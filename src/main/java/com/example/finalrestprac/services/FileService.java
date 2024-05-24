package com.example.finalrestprac.services;

import com.example.finalrestprac.properties.FileStorageProperty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@Getter
public class FileService {
//    private final Path storagePath;
//
//    @Autowired
//    public FileService(FileStorageProperty fileStorageProperty) {
//        this.storagePath = Paths.get(fileStorageProperty.getUploadDir()).toAbsolutePath().normalize();
//        try {
//            if (!Files.exists(storagePath)) {
//                Files.createDirectory(storagePath);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String saveFile(MultipartFile multipartFile) {
//        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        if (filename.contains("..")) {
//            throw new RuntimeException("Invalid filename");
//        }
//        try {
//            Path targetLocation = this.storagePath.resolve(filename);
//            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//            return filename;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Resource loadFile(String fileName) {
//        try {
//            Path filePath = this.getStoragePath().resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) {
//                return resource;
//            }
//            else{
//                throw new RuntimeException("File not found");
//            }
//        }catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }




//    private final Path storagePath;
//
//    @Autowired
//    public FileService(FileStorageProperty fileStorageProperty){
//        this.storagePath = Paths.get(fileStorageProperty.getUploadDir()).toAbsolutePath().normalize();
//        try{
//            if(!Files.exists(storagePath)){
//                Files.createDirectory(storagePath);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public String saveFile(MultipartFile file){
//        try{
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            if(fileName.contains("..")){
//                throw new RuntimeException("invalid name");
//            }
//            Path savePath = this.storagePath.resolve(fileName);
//            Files.copy(file.getInputStream(), savePath, StandardCopyOption.REPLACE_EXISTING);
//            return fileName;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public Resource loadFile(String fileName){
//        try{
//            Path targetPath = this.storagePath.resolve(fileName);
//            Resource resource = new UrlResource(targetPath.toUri());
//            if(resource.exists()){
//                return resource;
//            } else{
//              throw new RuntimeException("file not found");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    private final Path storagePath;

    @Autowired
    public FileService(FileStorageProperty fileStorageProperty){
        this.storagePath = Paths.get(fileStorageProperty.getUploadDir()).toAbsolutePath().normalize();
        try{
            if(!Files.exists(storagePath)){
                Files.createDirectory(storagePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String saveFile(MultipartFile multipartFile){
        if(multipartFile.getName().contains("..")){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid filename");
        }
        Path saveLocation = this.storagePath.resolve(multipartFile.getOriginalFilename());
        try{
            Files.copy(multipartFile.getInputStream(), saveLocation, StandardCopyOption.REPLACE_EXISTING);
            return multipartFile.getName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Resource loadFile(String name){
        try {
            Path resourcePath = this.storagePath.resolve(name);
            Resource resource = new UrlResource(resourcePath.toUri());
            if(resource.exists()){
                return resource;
            }
            else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "resource not found");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
















}