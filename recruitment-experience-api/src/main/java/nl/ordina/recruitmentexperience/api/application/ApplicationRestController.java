package nl.ordina.recruitmentexperience.api.application;

import nl.ordina.recruitmentexperience.api.application.model.ApplicationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationRestController {

    @GetMapping
    public List<ApplicationModel> getApplications() {
        return null;
    }

    @GetMapping("/{applicationId}")
    public ApplicationModel getApplication(@PathVariable final Long applicationId){
        return null;
    }
}
