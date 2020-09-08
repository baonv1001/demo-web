/*******************************************************************************
 * Copyright (c) 2020 iXchange Pte. Ltd. All rights reserved.
 *
 *  This software is the confidential and proprietary information of iXchange Pte
 *  Ltd ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the license
 *   agreement you entered into with iXchange.
 ******************************************************************************/

package com.webapp.webdemo.service;

import com.webapp.webdemo.payload.response.DocumentResponse;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {
    DocumentResponse uploadFile(MultipartFile files);

    Resource downloadFile(UsernamePasswordAuthenticationToken principal, Long documentNo);
}
