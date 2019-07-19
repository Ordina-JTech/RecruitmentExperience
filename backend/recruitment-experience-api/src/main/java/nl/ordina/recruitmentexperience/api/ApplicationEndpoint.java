package nl.ordina.recruitmentexperience.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.api.model.DocumentIdModel;
import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
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
    List<ApplicationIdModel> getApplications(@ApiParam(value = "Optional parameter to filter applications by state") String state,
    @ApiParam(value = "size of the data") int size, @ApiParam(value = "page number") int pageNo);

    @ApiOperation(value = "Get a single application")
    ApplicationIdModel getApplication(@ApiParam(value = "Id of the requested application") Long applicationId);

    @ApiOperation(value = "Find all notes for an application")
    List<NoteIdModel> getNotesByApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Create a new application")
    ApplicationIdModel postDocument(@ApiParam(value = "Creation data for the application") ApplicationIdModel application);

    @ApiOperation(value = "Update an existing application")
    ApplicationIdModel putApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Update data for the application") ApplicationIdModel application);

    @ApiOperation(value = "Create a new note")
    NoteIdModel postNote(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Creation data for the note") NoteIdModel note);

    @ApiOperation(value = "Update an existing note")
    NoteIdModel putNote(@ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Id of the corresponding note") Long noteId, @ApiParam(value = "Update data for the note") NoteIdModel note);

    @ApiOperation(value = "Send an application to the next state")
    ApplicationIdModel promoteApplication(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Upload a new document")
    DocumentIdModel postDocument(MultipartFile file, @ApiParam(value = "Id of the corresponding application") Long applicationId, @ApiParam(value = "Name of the author of the note") String author, @ApiParam(value = "Creation date of the note") String creationDate, @ApiParam(value = "Title of the note") String title);

    @ApiOperation(value = "Find all documents for an application")
    List<DocumentIdModel> getDocuments(@ApiParam(value = "Id of the corresponding application") Long applicationId);

    @ApiOperation(value = "Retrieve the file from the corresponding document")
    ResponseEntity<Resource> getFile(@ApiParam(value = "Id of the corresponding document") UUID documentId, @ApiParam(value = "Id of the corresponding application") Long applicationId);
}
