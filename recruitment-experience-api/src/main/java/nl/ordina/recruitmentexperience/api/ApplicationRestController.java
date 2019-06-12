package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToApplicationModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToNoteModelMapper;
import nl.ordina.recruitmentexperience.api.model.ApplicationModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.api.model.NoteModel;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import nl.ordina.recruitmentexperience.core.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationRestController {

    private final ApplicationService applicationService;

    private final ToApplicationModelMapper toApplicationModelMapper;

    private final ToNoteModelMapper toNoteModelMapper;

    private final NoteService noteService;

    @GetMapping
    public List<ApplicationModel> getApplications(@RequestParam(required = false) String state) {
        ApplicationStateModel stateModel;
        try {
            stateModel = ApplicationStateModel.valueOf(state.toUpperCase());
        } catch (NullPointerException e) {
            stateModel = null;
        }
        return toApplicationModelMapper.map(applicationService.getApplications((new FromApplicationStateModelMapper(ApplicationStateModel.class)).get(stateModel)));
    }

    @GetMapping("/{applicationId")
    public ApplicationModel getApplication(@PathVariable final Long applicationId) {
        return toApplicationModelMapper.map(applicationService.getApplication(applicationId));
    }

    @GetMapping("/{applicationId}/notes")
    public List<NoteModel> getNotesByApplication(@PathVariable final Long applicationId){
        return toNoteModelMapper.map(noteService.getNotesByApplication(applicationId));
    }
}
