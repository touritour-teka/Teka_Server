package com.teka.application.chat.port.in;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImageUseCase {
    String execute(MultipartFile file) throws IOException;
}
