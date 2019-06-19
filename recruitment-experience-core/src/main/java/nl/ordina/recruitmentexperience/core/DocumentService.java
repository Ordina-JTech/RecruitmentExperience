package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromDocumentEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Document;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.DocumentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final Path rootLocation = Paths.get("upload-dir");

    private final ApplicationRepository applicationRepository;

    private final DocumentRepository documentRepository;

    private final FromDocumentEntityMapper fromDocumentEntityMapper;

    public final Document postDocument(final Long applicationId, final MultipartFile file) {
//        final ApplicationEntity applicationEntity = applicationRepository.findOneById(applicationId);
//
//        final DocumentEntity documentEntity = DocumentEntity.builder()
//                .application(applicationEntity)
//                .title(documentId.getTitle())
//                .creationDate(documentId.getCreationDate())
//                .build();
//
//        final DocumentEntity savedDocument = documentRepository.save(documentEntity);

        store(file);

        return null;
//        return fromDocumentEntityMapper.map(savedDocument);
    }

    private void store(final MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
}
