package nl.ordina.recruitmentexperience.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.DocumentEntity;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * The purpose of this interface, is purely to make sure that {@link ApplicationRestController} doesn't get cluttered with Swagger annotations
 */
public interface ApplicationEndpoint {

    @ApiOperation(value = "Find all applications, optionally filter by state")
    List<ApplicationEntity> getApplications(@ApiParam(value = "Optional parameter to filter applications by state") String state);

    @ApiOperation(value = "Get a single application")
    ApplicationEntity getApplication(@ApiParam(value = "Id of the requested application") Long applicationId);

    @ApiOperation(value = "Find all notes for an application")
    List<NoteEntity> getNotesByApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Create a new application")
    ApplicationEntity postDocument(@ApiParam(value = "Creation data for the application") ApplicationEntity application);

    @ApiOperation(value = "Update an existing application")
    ApplicationEntity putApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Update data for the application") ApplicationEntity application);

    @ApiOperation(value = "Create a new note")
    NoteEntity postNote(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Creation data for the note") NoteEntity note);

    @ApiOperation(value = "Update an existing note")
    NoteEntity putNote(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Id of the corresponding note") Long noteId, @ApiParam(value = "Update data for the note") NoteEntity noteEntity);

    @ApiOperation(value = "Send an application to the next state")
    ApplicationEntity promoteApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Upload a new document")
    DocumentEntity postDocument(MultipartFile file, @ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Name of the author of the note") String author, @ApiParam(value = "Creation date of the note") String creationDate, @ApiParam(value = "Title of the note") String title);

    @ApiOperation(value = "Find all documents for an application")
    List<DocumentEntity> getDocuments(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Retrieve the file from the corresponding document")
    ResponseEntity<Resource> getFile(@ApiParam(value = "Id of the corresponding document") UUID documentId, @ApiParam(value = "Id of the corresponding application") Long applicationId);
}
