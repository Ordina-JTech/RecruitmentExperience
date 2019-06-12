package nl.ordina.recruitmentexperience.app;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicantRepository;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import nl.ordina.recruitmentexperience.data.application.repository.NoteRepository;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;
    private final BusinessUnitManagerRepository businessUnitManagerRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final NoteRepository noteRepository;
    private final RegionRepository regionRepository;

    @Override
    public void run(String... strings) {
        final ApplicantEntity applicantEntity = ApplicantEntity.builder()
                .firstName("firstname")
                .prefix("prefix")
                .lastName("lastname")
                .email("email")
                .phoneNumber("phone")
                .resumeLink("resumelink")
                .build();

        final BusinessUnitEntity businessUnitEntity = BusinessUnitEntity.builder()
                .name("bu")
                .build();

        final BusinessUnitManagerEntity businessUnitManagerEntity = BusinessUnitManagerEntity.builder()
                .firstName("bumfirst")
                .prefix("bumprefix")
                .lastName("bumlast")
                .email("email")
                .businessUnit(businessUnitEntity)
                .build();

        final RegionEntity regionEntity = RegionEntity.builder()
                .name("region")
                .build();

        final ApplicationEntity applicationEntity = ApplicationEntity.builder()
                .state("CONTRACT")
                .firstInterviewDateTime(LocalDateTime.now())
                .secondInterviewDateTime(LocalDateTime.now())
                .motivationLetterLink("link")
                .applicant(applicantEntity)
                .businessUnit(businessUnitEntity)
                .businessUnitManager(businessUnitManagerEntity)
                .region(regionEntity)
                .title("title")
                .build();

        final NoteEntity noteEntity = NoteEntity.builder()
                .author("author")
                .title("title")
                .text("text")
                .application(applicationEntity)
                .creationDate(ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")).toOffsetDateTime().toString())
                .build();

        applicantRepository.save(applicantEntity);
        businessUnitRepository.save(businessUnitEntity);
        businessUnitManagerRepository.save(businessUnitManagerEntity);
        regionRepository.save(regionEntity);
        applicationRepository.save(applicationEntity);
        noteRepository.save(noteEntity);

    }
}
