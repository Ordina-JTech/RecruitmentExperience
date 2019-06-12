package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/application-states")
@RequiredArgsConstructor
public class ApplicationStateRestController {

    private final ApplicationService applicationService;

    @GetMapping
    public Map<String, Long> getApplicationStateCount(){
        return applicationService.getApplicationStateCount();
    }

}
