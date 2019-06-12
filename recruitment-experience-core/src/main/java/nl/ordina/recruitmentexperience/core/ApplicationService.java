package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromApplicationEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.ApplicationState;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final FromApplicationEntityMapper fromApplicationEntityMapper;

    public List<Application> getApplications(final ApplicationState stateFilter) {
        final List<ApplicationEntity> applicationEntities;

        if(stateFilter != null) {
            applicationEntities = applicationRepository.findAllByState(stateFilter.name());
        } else {
            applicationEntities = applicationRepository.findAll();
        }

        return fromApplicationEntityMapper.map(applicationEntities);
    }

    public Application getApplication(final Long id) {
        return fromApplicationEntityMapper.map(applicationRepository.findOneById(id));
    }

    public Map<String, Long> getApplicationStateCount() {
        Map<String, Long> counts = new HashMap<>();

        Arrays.stream(ApplicationState.values()).map(Enum::name).forEach(state -> counts.put(state, applicationRepository.countByState(state)));

        return counts;
    }
}
