package nl.ordina.recruitmentexperience.api.application;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.application.mapper.ToApplicationModelMapper;
import nl.ordina.recruitmentexperience.api.application.model.ApplicationModel;
import nl.ordina.recruitmentexperience.core.application.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationRestController {

    private final ApplicationService applicationService;

    private final ToApplicationModelMapper toApplicationModelMapper;

    @GetMapping
    public List<ApplicationModel> getApplications() {
        return toApplicationModelMapper.map(applicationService.getApplications());
    }

    @GetMapping("/{applicationId}")
    public ApplicationModel getApplication(@PathVariable final Long applicationId){
        return null;
    }
}
