package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.FromDocumentIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.FromNoteIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToApplicationIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToDocumentIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToNoteIdModelMapper;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.api.model.DocumentIdModel;
import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import nl.ordina.recruitmentexperience.core.DocumentService;
import nl.ordina.recruitmentexperience.core.NoteService;
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

    private final ToApplicationIdModelMapper toApplicationIdModelMapper;

    private final ToNoteIdModelMapper toNoteIdModelMapper;

    private final NoteService noteService;

    private final FromApplicationIdModelMapper fromApplicationIdModelMapper;

    private final FromApplicationStateModelMapper fromApplicationStateModelMapper;

    private final FromNoteIdModelMapper fromNoteIdModelMapper;

    private final DocumentService documentService;

    private final FromDocumentIdModelMapper fromDocumentIdModelMapper;

    private final ToDocumentIdModelMapper toDocumentIdModelMapper;

    @Override
    @GetMapping
    public List<ApplicationIdModel> getApplications(@RequestParam(required = false) String state) {
        ApplicationStateModel stateModel;
        try {
            stateModel = ApplicationStateModel.valueOf(state.toUpperCase());
        } catch (NullPointerException e) {
            stateModel = null;
        }
        return toApplicationIdModelMapper.map(applicationService.getApplications(fromApplicationStateModelMapper.mapNullSafe(stateModel)));
    }

    @Override
    @GetMapping("/{applicationId}")
    public ApplicationIdModel getApplication(@PathVariable final Long applicationId) {
        return toApplicationIdModelMapper.map(applicationService.getApplication(applicationId));
    }

    @Override
    @GetMapping("/{applicationId}/notes")
    public List<NoteIdModel> getNotesByApplication(@PathVariable final Long applicationId){
        return toNoteIdModelMapper.map(noteService.getNotesByApplication(applicationId));
    }

    @Override
    @PostMapping
    public ApplicationIdModel postDocument(@RequestBody final ApplicationIdModel application) {
        application.setId(null);
        application.getApplicant().setId(null);
        return toApplicationIdModelMapper.map(applicationService.postApplication(fromApplicationIdModelMapper.map(application)));
    }

    @Override
    @PutMapping("/{applicationId}")
    public ApplicationIdModel putApplication(@PathVariable final Long applicationId, @RequestBody final ApplicationIdModel application) {
        application.setId(applicationId);
        return toApplicationIdModelMapper.map(applicationService.putApplication(fromApplicationIdModelMapper.map(application)));
    }

    @Override
    @PostMapping("/{applicationId}/notes")
    public NoteIdModel postNote(@PathVariable final Long applicationId, @RequestBody final NoteIdModel note) {
        note.setApplicationId(applicationId);
        note.setId(null);
        return toNoteIdModelMapper.map(noteService.postNote(fromNoteIdModelMapper.map(note)));
    }

    @Override
    @PutMapping("/{applicationId}/notes/{noteId}")
    public NoteIdModel putNote(@PathVariable final Long applicationId, @PathVariable Long noteId, @RequestBody final NoteIdModel noteIdModel) {
        noteIdModel.setId(noteId);
        return toNoteIdModelMapper.map(noteService.putNote(fromNoteIdModelMapper.map(noteIdModel)));
    }

    @Override
    @PostMapping("/{applicationId}/promote")
    public ApplicationIdModel promoteApplication(@PathVariable final Long applicationId){
        return toApplicationIdModelMapper.map(applicationService.promoteApplication(applicationId));
    }

    @Override
    @PostMapping("/{applicationId}/documents")
    public DocumentIdModel postDocument(@RequestParam("file") final MultipartFile file, @PathVariable final Long applicationId, @RequestParam("author") final String author, @RequestParam("creationDate") final String creationDate, @RequestParam("title") final String title) {
        final DocumentIdModel documentIdModel = new DocumentIdModel();
        documentIdModel.setTitle(title);
        documentIdModel.setCreationDate(creationDate);
        documentIdModel.setApplicationId(applicationId);
        documentIdModel.setAuthor(author);

        return toDocumentIdModelMapper.map(documentService.postDocument(applicationId, fromDocumentIdModelMapper.map(documentIdModel), file));
    }

    @Override
    @GetMapping("/{applicationId}/documents")
    public List<DocumentIdModel> getDocuments(@PathVariable final Long applicationId) {
        return toDocumentIdModelMapper.map(documentService.getDocuments(applicationId));
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
