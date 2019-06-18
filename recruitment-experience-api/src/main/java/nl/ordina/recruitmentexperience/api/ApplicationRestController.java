package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.FromNoteIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToApplicationIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToNoteIdModelMapper;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import nl.ordina.recruitmentexperience.core.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationRestController {

    private final ApplicationService applicationService;

    private final ToApplicationIdModelMapper toApplicationIdModelMapper;

    private final ToNoteIdModelMapper toNoteIdModelMapper;

    private final NoteService noteService;

    private final FromApplicationIdModelMapper fromApplicationIdModelMapper;

    private final FromApplicationStateModelMapper fromApplicationStateModelMapper;

    private final FromNoteIdModelMapper fromNoteIdModelMapper;

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

    @GetMapping("/{applicationId}")
    public ApplicationIdModel getApplication(@PathVariable final Long applicationId) {
        return toApplicationIdModelMapper.map(applicationService.getApplication(applicationId));
    }

    @GetMapping("/{applicationId}/notes")
    public List<NoteIdModel> getNotesByApplication(@PathVariable final Long applicationId){
        return toNoteIdModelMapper.map(noteService.getNotesByApplication(applicationId));
    }

    @PostMapping
    public ApplicationIdModel postApplication(@RequestBody final ApplicationIdModel applicationIdModel) {
        applicationIdModel.setId(null);
        applicationIdModel.getApplicant().setId(null);
        return toApplicationIdModelMapper.map(applicationService.postApplication(fromApplicationIdModelMapper.map(applicationIdModel)));
    }

    @PutMapping("/{applicationId}")
    public ApplicationIdModel putApplication(@PathVariable final Long applicationId, @RequestBody final ApplicationIdModel applicationIdModel) {
        applicationIdModel.setId(applicationId);
        return toApplicationIdModelMapper.map(applicationService.putApplication(fromApplicationIdModelMapper.map(applicationIdModel)));
    }

    @PostMapping("/{applicationId}/notes")
    public NoteIdModel postNote(@PathVariable final Long applicationId, @RequestBody final NoteIdModel noteIdModel) {
        noteIdModel.setApplicationId(applicationId);
        noteIdModel.setId(null);
        return toNoteIdModelMapper.map(noteService.postNote(fromNoteIdModelMapper.map(noteIdModel)));
    }

    @PostMapping("/{applicationId}/promote")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void promoteApplication(@PathVariable final Long applicationId){
        applicationService.promoteApplication(applicationId);
    }
}
