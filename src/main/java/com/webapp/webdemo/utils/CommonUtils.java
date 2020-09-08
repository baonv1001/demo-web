package com.webapp.webdemo.utils;

import io.jsonwebtoken.impl.TextCodec;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
    public static String encodeTextByBASE64(String value){
        return TextCodec.BASE64.encode(value);
    }
}
