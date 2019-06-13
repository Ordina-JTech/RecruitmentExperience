package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromApplicationEntityMapper;
import nl.ordina.recruitmentexperience.core.mapper.ToApplicantEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Applicant;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.ApplicationId;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;
import nl.ordina.recruitmentexperience.core.model.state.State;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicantRepository;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import nl.ordina.recruitmentexperience.data.application.repository.DepartmentRepository;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
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

    private final RegionRepository regionRepository;

    private final DepartmentRepository departmentRepository;

    private final BusinessUnitRepository businessUnitRepository;

    private final BusinessUnitManagerRepository businessUnitManagerRepository;

    private final ApplicantRepository applicantRepository;

    private final ToApplicantEntityMapper toApplicantEntityMapper;

    public List<Application> getApplications(final State stateFilter) {
        final List<ApplicationEntity> applicationEntities;

        if(stateFilter != null) {
            applicationEntities = applicationRepository.findAllByState(stateFilter.toEnum().name());
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

    public Application postApplication(final ApplicationId applicationId) {
        final Applicant applicant = applicationId.getApplicant();

        final ApplicantEntity savedApplicant = applicantRepository.save(toApplicantEntityMapper.map(applicant));

        final ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .id(applicationId.getId())
                .applicant(savedApplicant)
                .firstInterviewDateTime(applicationId.getFirstInterviewDateTime().toString())
                .secondInterviewDateTime(applicationId.getSecondInterviewDateTime().toString())
                .motivationLetterLink(applicationId.getMotivationLetterLink())
                .title(applicationId.getTitle())
                .state(applicationId.getState().toEnum().name())
                .region(regionRepository.findOneById(applicationId.getRegionId()))
                .department(departmentRepository.findOneById(applicationId.getDepartmentId()))
                .businessUnit(businessUnitRepository.findOneById(applicationId.getBusinessUnitId()))
                .businessUnitManager(businessUnitManagerRepository.findOneById(applicationId.getBusinessUnitManagerId()))
                .build();

        final ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        return fromApplicationEntityMapper.map(savedApplication);
    }
}
