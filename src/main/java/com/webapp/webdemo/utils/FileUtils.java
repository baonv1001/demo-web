package com.webapp.webdemo.utils;

import com.webapp.webdemo.constants.CommonConstants;
import com.webapp.webdemo.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Slf4j
public class FileUtils {

    private FileUtils() {
    }

    public static Path storeFile(Path fileStorageLocation, MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        // Check if type file's invalid
        if (!validateFile(fileName)) {
            throw new AppException("Invalid file type.");
        }

        // converts a given path string to a Path
        Path targetLocation = fileStorageLocation.resolve(fileName);

        // Rename file if file exists
        int count = 0;
        String newFileName;
        while (Files.exists(targetLocation)) {
            count++;
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0, index).concat("_" + count + fileName.substring(index));
            targetLocation = fileStorageLocation.resolve(newFileName);
        }

        try {
            log.info("Store file to the target location: {}", targetLocation.toAbsolutePath().normalize().toString());
            Files.copy(file.getInputStream(), targetLocation);
        } catch (IOException ex) {
            throw new AppException("Could not store file.");
        }
        return targetLocation;
    }

    public static boolean validateFile(String fileName){
        String[] typeFiles = CommonConstants.FILES_TYPE.split(CommonConstants.CommonRegex.COMMA_SPACE);

        // Lower case file name
        fileName = fileName.toLowerCase();
        for (String typeFile : typeFiles) {
            if (fileName.endsWith(typeFile)) {
                return true;
            }
        }
        return false;
    }

    public static Resource getResource(String url, String fileName) {
        Path filePath = Paths.get(url).normalize();
        if (Files.notExists(filePath)) {
            throw new AppException("File not found: " + fileName);
        }
        try {
            return new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new AppException("File not found: " + fileName);
        }
    }

    public static ResponseEntity<Resource> responseResourceEntity(Resource resource, String fileName) {
        if (fileName != null) {
            fileName = fileName.replace(CommonConstants.ENCODE_CHARACTER_SPACE, CommonConstants.WHITE_SPACE_CHARACTER);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, CommonConstants.ATTACHMENT_FILENAME + fileName + CommonConstants.CommonRegex.KEY_SLASH)
                .body(resource);
    }

    public static Path getPath(String directory) {
        // Create the Path instance
        Path fileStorageLocation = Paths.get(directory).toAbsolutePath().normalize();
        try {
            // Create dir to store file
            if (!fileStorageLocation.toFile().exists()) {
                Files.createDirectories(fileStorageLocation);
            }
        } catch (Exception e) {
            log.error("Could not create the directory where the uploaded files will be stored." + e);
        }
        return fileStorageLocation;
    }
}
