package nl.ordina.recruitmentexperience.api.application;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.application.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.application.mapper.ToApplicationModelMapper;
import nl.ordina.recruitmentexperience.api.application.model.ApplicationModel;
import nl.ordina.recruitmentexperience.api.application.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.core.application.ApplicationService;
import nl.ordina.recruitmentexperience.core.application.model.Application;
import nl.ordina.recruitmentexperience.core.application.model.ApplicationState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationRestController {

    private final ApplicationService applicationService;

    private final ToApplicationModelMapper toApplicationModelMapper;

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

    @GetMapping("/{applicationId}")
    public ApplicationModel getApplication(@PathVariable final Long applicationId){
        return null;
    }
}
