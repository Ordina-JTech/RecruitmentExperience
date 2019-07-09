package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import nl.ordina.recruitmentexperience.core.DocumentService;
import nl.ordina.recruitmentexperience.core.NoteService;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.DocumentEntity;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationRestController implements ApplicationEndpoint {

    private final ApplicationService applicationService;
    private final NoteService noteService;
    private final DocumentService documentService;

    @Override
    @GetMapping
    public List<ApplicationEntity> getApplications(@RequestParam(required = false) String state) {
        return applicationService.getApplications();
    }

    @Override
    @GetMapping("/{applicationId}")
    public ApplicationEntity getApplication(@PathVariable final Long applicationId) {
        return applicationService.getApplication(applicationId);
    }

    @Override
    @GetMapping("/{applicationId}/notes")
    public List<NoteEntity> getNotesByApplication(@PathVariable final Long applicationId){
        return noteService.getNotesByApplication(applicationId);
    }

    @Override
    @PostMapping
    public ApplicationEntity postDocument(@RequestBody final ApplicationEntity application) {
        application.setId(null);
        application.getApplicant().setId(null);
        return applicationService.postApplication(application);
    }

    @Override
    @PutMapping("/{applicationId}")
    public ApplicationEntity putApplication(@PathVariable final Long applicationId, @RequestBody final ApplicationEntity application) {
        application.setId(applicationId);
        return applicationService.putApplication(application);
    }

    @Override
    @PostMapping("/{applicationId}/notes")
    public NoteEntity postNote(@PathVariable final Long applicationId, @RequestBody final NoteEntity noteEntity) {
        //note.setApplicationId(applicationId);
        noteEntity.setId(null);
        return noteService.postNote(noteEntity);
    }

    @Override
    @PutMapping("/{applicationId}/notes/{noteId}")
    public NoteEntity putNote(@PathVariable final Long applicationId, @PathVariable Long noteId, @RequestBody final NoteEntity noteEntity) {
        noteEntity.setId(noteId);
        return noteService.putNote(noteEntity);
    }

    @Override
    @PostMapping("/{applicationId}/promote")
    public ApplicationEntity promoteApplication(@PathVariable final Long applicationId){
        return applicationService.promoteApplication(applicationId);
    }

    @Override
    @PostMapping("/{applicationId}/documents")
    public DocumentEntity postDocument(@RequestParam("file") final MultipartFile file, @PathVariable final Long applicationId, @RequestParam("author") final String author, @RequestParam("creationDate") final String creationDate, @RequestParam("title") final String title) {
        final DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setTitle(title);
        documentEntity.setCreationDate(creationDate);
        //documentEntity.setApplicationId(applicationId);
        //documentEntity.setAuthor(author);
        return documentService.postDocument(applicationId, documentEntity, file);
    }

    @Override
    @GetMapping("/{applicationId}/documents")
    public List<DocumentEntity> getDocuments(@PathVariable final Long applicationId) {
        return documentService.getDocuments(applicationId);
    }

    @Override
    @GetMapping("/{applicationId}/documents/{documentId}")
    public ResponseEntity<Resource> getFile(@PathVariable final UUID documentId, @PathVariable final Long applicationId) {
        final Resource file = documentService.getDocument(documentId);

        String extension = FilenameUtils.getExtension(file.getFilename());

        if("docx".equals(extension) || "doc".equals(extension)) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file);
        }

        MediaType mediaType;

        switch(extension) {
            case "pdf":
                mediaType = MediaType.APPLICATION_PDF;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            case "jpg":
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "gif":
                mediaType = MediaType.IMAGE_GIF;
                break;
            default:
                throw new IllegalStateException();
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
