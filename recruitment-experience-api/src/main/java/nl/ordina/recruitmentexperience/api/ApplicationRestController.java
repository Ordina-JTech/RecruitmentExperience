package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToApplicationIdModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToNoteIdModelMapper;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.api.model.NoteIdModel;
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

    private final ToApplicationIdModelMapper toApplicationIdModelMapper;

    private final ToNoteIdModelMapper toNoteIdModelMapper;

    private final NoteService noteService;

    @GetMapping
    public List<ApplicationIdModel> getApplications(@RequestParam(required = false) String state) {
        ApplicationStateModel stateModel;
        try {
            stateModel = ApplicationStateModel.valueOf(state.toUpperCase());
        } catch (NullPointerException e) {
            stateModel = null;
        }
        return toApplicationIdModelMapper.map(applicationService.getApplications((new FromApplicationStateModelMapper(ApplicationStateModel.class)).get(stateModel)));
    }

    @GetMapping("/{applicationId}")
    public ApplicationIdModel getApplication(@PathVariable final Long applicationId) {
        return toApplicationIdModelMapper.map(applicationService.getApplication(applicationId));
    }

    @GetMapping("/{applicationId}/notes")
    public List<NoteIdModel> getNotesByApplication(@PathVariable final Long applicationId){
        return toNoteIdModelMapper.map(noteService.getNotesByApplication(applicationId));
    }
}
