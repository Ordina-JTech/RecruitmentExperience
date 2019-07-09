package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.DocumentEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.DocumentRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final Path rootLocation = Paths.get("upload-dir");

    private final ApplicationRepository applicationRepository;

    private final DocumentRepository documentRepository;

    public final DocumentEntity postDocument(final Long applicationId, final DocumentEntity documentEntity, final MultipartFile file) {
        final ApplicationEntity applicationEntity = applicationRepository.findOneById(applicationId);

        final DocumentEntity documentEntityNew = DocumentEntity.builder()
                .id(UUID.randomUUID())
                .application(applicationEntity)
                .title(documentEntity.getTitle())
                .creationDate(documentEntity.getCreationDate())
                .extension(FilenameUtils.getExtension(file.getOriginalFilename()))
                .build();

        final DocumentEntity savedDocument = documentRepository.save(documentEntityNew);
        store(file, savedDocument.getId());
        return savedDocument;
    }

    public Resource getDocument(final UUID documentId){
        final DocumentEntity documentEntity = documentRepository.findOneById(documentId);
        return loadFile(String.format("%s.%s", documentEntity.getId().toString(), documentEntity.getExtension()));
    }

    private void store(final MultipartFile file, final UUID fileId) {
        try {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            Files.copy(file.getInputStream(), this.rootLocation.resolve(String.format("%s.%s",fileId.toString(), extension)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }

    public List<DocumentEntity> getDocuments(Long applicationId) {
        return documentRepository.findAllByApplication_Id(applicationId);
    }
}
