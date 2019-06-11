package nl.ordina.recruitmentexperience.core.application;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.application.mapper.FromApplicationEntityMapper;
import nl.ordina.recruitmentexperience.core.application.model.Application;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    private final FromApplicationEntityMapper fromApplicationEntityMapper;

    public List<Application> getApplications() {
        return fromApplicationEntityMapper.map(applicationRepository.findAll());
    }
}
