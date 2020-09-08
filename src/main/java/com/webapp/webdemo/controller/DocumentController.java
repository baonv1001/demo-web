package com.webapp.webdemo.controller;

import com.webapp.webdemo.payload.response.DocumentResponse;
import com.webapp.webdemo.service.DocumentService;
import com.webapp.webdemo.utils.FileUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/web/document")
public class DocumentController extends AbstractController{
    private final DocumentService documentService;

    @ApiOperation(value = "Upload Document", notes = "POST method only")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = DocumentResponse.class, message = "CREATED")
    })
    @PostMapping
    public ResponseEntity<DocumentResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("Starting upload Document API");
        return new ResponseEntity<>(this.documentService.uploadFile(file), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Download Document", notes = "GET method only")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = Resource.class, message = "GET")
    })
    @GetMapping(("/download-file/{document-no}"))
    public ResponseEntity<Resource> downloadFile(@PathVariable(value = "document-no") Long documentNo, Principal principal){
        log.info("Starting download Document API");
        Resource resource = this.documentService.downloadFile((UsernamePasswordAuthenticationToken) principal, documentNo);
        return FileUtils.responseResourceEntity(resource, resource.getFilename());
    }
}
