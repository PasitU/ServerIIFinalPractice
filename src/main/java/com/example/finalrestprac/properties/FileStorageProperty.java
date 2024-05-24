package com.example.finalrestprac.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
@Getter
@Setter
public class FileStorageProperty {
    private String uploadDir;
}
