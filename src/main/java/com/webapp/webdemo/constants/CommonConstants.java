package com.webapp.webdemo.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonConstants {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class HeaderConstants{
        public static final String APP_TOKEN = "App-Token";
        public static final String BEARER_TOKEN = "Bearer ";
        public static final String HEADER = "header";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CommonRegex{
        public static final String COMMA_SPACE = ", ";
        public static final String KEY_SLASH = "\"";
    }

    /**
     * FILE
     */
    public static final String FILES_TYPE = ".doc, .docx, .xls, .xlsx, .csv, .pdf, .png, .jpeg";
    public static final String ATTACHMENT_FILENAME = "attachment; filename=\"";

    /**
     * GENERAL
     */
    public static final String ENCODE_CHARACTER_SPACE = "%20";
    public static final String WHITE_SPACE_CHARACTER = " ";
}
