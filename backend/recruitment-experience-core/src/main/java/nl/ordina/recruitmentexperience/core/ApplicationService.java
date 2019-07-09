package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;
import nl.ordina.recruitmentexperience.core.video.VideoRenderingService;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicantRepository;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import nl.ordina.recruitmentexperience.data.application.repository.DepartmentRepository;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final BusinessUnitManagerRepository businessUnitManagerRepository;
    private final ApplicantRepository applicantRepository;
    private VideoRenderingService videoRenderingService;

    public List<ApplicationEntity> getApplications() {
        return applicationRepository.findAll();
    }

    public ApplicationEntity getApplication(final Long id) {
        return applicationRepository.findOneById(id);
    }

    public Map<String, Long> getApplicationStateCount() {
        Map<String, Long> counts = new HashMap<>();
        Arrays.stream(ApplicationState.values()).map(Enum::name).forEach(state -> counts.put(state, applicationRepository.countByState(state)));
        return counts;
    }

    public ApplicationEntity postApplication(final ApplicationEntity applicationEntity) {
        //final ApplicantEntity applicantEntity = applicationId.getApplicant();

        //final ApplicantEntity savedApplicant = applicantRepository.save(toApplicantEntityMapper.map(applicant));

        final OffsetDateTime firstInterviewDateTime = OffsetDateTime.parse(applicationEntity.getFirstInterviewDateTime());
        final OffsetDateTime secondInterviewDateTime = OffsetDateTime.parse(applicationEntity.getSecondInterviewDateTime());

        final ApplicationEntity applicationEntityToSave = ApplicationEntity.builder()
                //.id(applicationEntity.getId())
                //.applicant(savedApplicant)
                .firstInterviewDateTime(firstInterviewDateTime == null ? null : firstInterviewDateTime.toString())
                .secondInterviewDateTime(secondInterviewDateTime == null ? null : secondInterviewDateTime.toString())
                .motivationLetterLink(applicationEntity.getMotivationLetterLink())
                .title(applicationEntity.getTitle())
                //.state(applicationEntity.getState().toEnum().name())
                .region(regionRepository.findOneById(applicationEntity.getRegion().getId()))
                .department(departmentRepository.findOneById(applicationEntity.getDepartment().getId()))
                .businessUnit(businessUnitRepository.findOneById(applicationEntity.getBusinessUnit().getId()))
                .businessUnitManager(businessUnitManagerRepository.findOneById(applicationEntity.getBusinessUnitManager().getId()))
                .build();

        return applicationRepository.save(applicationEntityToSave);
    }

    public ApplicationEntity putApplication(final ApplicationEntity appEntity) {
        final ApplicationEntity applicationEntity = applicationRepository.findOneById(appEntity.getId());

        final ApplicantEntity applicantEntity = applicantRepository.findOneById(appEntity.getApplicant().getId());
        applicantEntity.setEmail(appEntity.getApplicant().getEmail());
        applicantEntity.setFirstName(appEntity.getApplicant().getFirstName());
        applicantEntity.setPrefix(appEntity.getApplicant().getPrefix());
        applicantEntity.setLastName(appEntity.getApplicant().getLastName());
        applicantEntity.setPhoneNumber(appEntity.getApplicant().getPhoneNumber());
        applicantEntity.setResumeLink(appEntity.getApplicant().getResumeLink());
        applicantEntity.setEmail(appEntity.getApplicant().getEmail());

        final ApplicantEntity savedApplicant = applicantRepository.save(applicantEntity);

        final OffsetDateTime firstInterviewDateTime = OffsetDateTime.parse(appEntity.getFirstInterviewDateTime());
        final OffsetDateTime secondInterviewDateTime = OffsetDateTime.parse(appEntity.getSecondInterviewDateTime());

        applicationEntity.setApplicant(savedApplicant);
        applicationEntity.setFirstInterviewDateTime(firstInterviewDateTime == null ? null : firstInterviewDateTime.toString());
        applicationEntity.setSecondInterviewDateTime(secondInterviewDateTime == null ? null : secondInterviewDateTime.toString());
        applicationEntity.setMotivationLetterLink(appEntity.getMotivationLetterLink());
        applicationEntity.setTitle(appEntity.getTitle());
        //applicationEntity.setState(appEntity.getState().toEnum().name());
        applicationEntity.setRegion(regionRepository.findOneById(appEntity.getRegion().getId()));
        applicationEntity.setDepartment(departmentRepository.findOneById(appEntity.getDepartment().getId()));
        applicationEntity.setBusinessUnit(businessUnitRepository.findOneById(appEntity.getBusinessUnit().getId()));
        applicationEntity.setBusinessUnitManager(businessUnitManagerRepository.findOneById(appEntity.getBusinessUnitManager().getId()));

        final ApplicationEntity savedApplication = applicationRepository.save(applicationEntity);

        return savedApplication;
    }

    public ApplicationEntity promoteApplication(final Long applicationid) {
        //final ApplicationEntity applicationEntity = applicationRepository.findOneById(applicationid);
        //final Application application = fromApplicationEntityMapper.map(applicationEntity);
        //application.getState().toNextState(application);
        //final ApplicationEntity savedApplication = applicationRepository.save(toApplicationEntityMapper.map(application));
        //return fromApplicationEntityMapper.map(savedApplication);
        return null;
    }
}
