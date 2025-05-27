package com.teka.application.chat.service;

import com.teka.application.chat.exception.EmptyFileException;
import com.teka.application.chat.exception.FileSizeLimitExceededException;
import com.teka.application.chat.exception.MediaTypeMismatchException;
import com.teka.application.chat.port.in.UploadImageUseCase;
import com.teka.shared.constants.FileConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.teka.shared.constants.FileConstant.MB;

@RequiredArgsConstructor
@Service
public class UploadImageService implements UploadImageUseCase {

    private final Path path = Paths.get(System.getProperty("user.dir"), FileConstant.UPLOAD_DIR);

    @Override
    public String execute(MultipartFile file) throws IOException {
        validate(file);
        Files.createDirectories(path);

        String filename = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
        String fullFileName = UUID.randomUUID() + "_" + filename;

        Path fullPath = path.resolve(fullFileName);
        Files.write(fullPath, file.getBytes());

        return fullFileName;
    }

    private void validate(MultipartFile file) {
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }

        MediaType webp = MediaType.parseMediaType("image/webp");
        List<MediaType> mediaTypeList = List.of(MediaType.IMAGE_JPEG, MediaType.IMAGE_PNG, MediaType.IMAGE_GIF, webp);
        if (!mediaTypeList.contains(MediaType.parseMediaType(Objects.requireNonNull(file.getContentType())))) {
            throw new MediaTypeMismatchException();
        }

        if (file.getSize() > 5 * MB) {
            throw new FileSizeLimitExceededException();
        }
    }
}
