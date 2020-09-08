package com.webapp.webdemo.config;

import com.webapp.webdemo.utils.FileUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;

@ConfigurationProperties(prefix = "file")
@Component
@Getter
@Setter
public class FileStorageProperties {
    private String uploadDir;

    @Bean
    public Path getUploadPath(){
        return FileUtils.getPath(uploadDir + File.separator + LocalDate.now());
    }
}
