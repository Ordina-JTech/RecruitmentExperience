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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan(basePackages = "nl.ordina.recruitmentexperience.data")
@ComponentScan(basePackages = "nl.ordina.recruitmentexperience")
@EnableJpaRepositories(basePackages = "nl.ordina.recruitmentexperience.data")
public class RecruitmentExperienceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitmentExperienceApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    class DbInitializer implements CommandLineRunner {
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
                    .build();

            final NoteEntity noteEntity = NoteEntity.builder()
                    .author("author")
                    .title("title")
                    .comments("comments")
                    .build();

            applicantRepository.save(applicantEntity);
            businessUnitRepository.save(businessUnitEntity);
            businessUnitManagerRepository.save(businessUnitManagerEntity);
            regionRepository.save(regionEntity);
            applicationRepository.save(applicationEntity);
            noteRepository.save(noteEntity);

        }
    }
}
