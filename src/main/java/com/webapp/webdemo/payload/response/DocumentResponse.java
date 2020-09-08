package com.webapp.webdemo.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DocumentResponse {
    private Long documentNo;
    private String fileName;
}
