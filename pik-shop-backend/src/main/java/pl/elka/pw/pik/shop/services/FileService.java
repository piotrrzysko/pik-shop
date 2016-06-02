package pl.elka.pw.pik.shop.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.elka.pw.pik.shop.domain.model.File;
import pl.elka.pw.pik.shop.domain.repository.FileRepository;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FileService {
    private static final String FILE_PREFIX = "file_";

    @Value("${files.repository.root}")
    private String filesRepositoryRoot;

    private FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public Set<File> upload(List<MultipartFile> files) {
        if (files != null) {
            return files.stream().filter(f -> !f.isEmpty()).map(this::upload).collect(Collectors.toSet());
        }
        return Collections.emptySet();
    }

    private File upload(MultipartFile file) {
        try {
            String fileName = copyToRepository(file);
            File uploadedFile = new File(fileName);
            return fileRepository.save(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when uploading file: " + file.getOriginalFilename());
        }
    }

    private String copyToRepository(MultipartFile file) throws IOException {
        String suffixExtension = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        java.io.File newFile = java.io.File.createTempFile(FILE_PREFIX, suffixExtension, new java.io.File(filesRepositoryRoot));

        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile))) {
            FileCopyUtils.copy(file.getInputStream(), stream);
        }
        return newFile.getName();
    }
}
