package nl.ordina.recruitmentexperience.app;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;
import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import nl.ordina.recruitmentexperience.data.application.model.DepartmentEntity;
import nl.ordina.recruitmentexperience.data.application.model.NoteEntity;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicantRepository;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import nl.ordina.recruitmentexperience.data.application.repository.DepartmentRepository;
import nl.ordina.recruitmentexperience.data.application.repository.NoteRepository;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DbInitializer implements CommandLineRunner {

    private final ApplicantRepository applicantRepository;
    private final ApplicationRepository applicationRepository;
    private final BusinessUnitManagerRepository businessUnitManagerRepository;
    private final BusinessUnitRepository businessUnitRepository;
    private final NoteRepository noteRepository;
    private final RegionRepository regionRepository;
    private final DepartmentRepository departmentRepository;
    private final Fairy fairy = Fairy.create();

    @Override
    public void run(String... strings) {

        List<ApplicantEntity> applicants = new ArrayList<>();
        List<ApplicationEntity> applications = new ArrayList<>();
        List<BusinessUnitEntity> businessUnits = new ArrayList<>();
        List<BusinessUnitManagerEntity> businessUnitManagers = new ArrayList<>();
        List<RegionEntity> regions = new ArrayList<>();
        List<NoteEntity> notes = new ArrayList<>();
        List<DepartmentEntity> departments = new ArrayList<>();

        final DepartmentEntity d1 = DepartmentEntity.builder().name("OSD").build();
        final DepartmentEntity d2 = DepartmentEntity.builder().name("T&S").build();

        final DepartmentEntity savedD1 = departmentRepository.save(d1);
        final DepartmentEntity savedD2 = departmentRepository.save(d2);

        departments.add(savedD1);
        departments.add(savedD2);

        final BusinessUnitEntity bu1 = BusinessUnitEntity.builder().department(savedD1).name("JTech").build();
        final BusinessUnitEntity bu2 = BusinessUnitEntity.builder().department(savedD2).name("MTech").build();
        final BusinessUnitEntity bu3 = BusinessUnitEntity.builder().department(savedD1).name("JSRoots").build();
        final BusinessUnitEntity bu4 = BusinessUnitEntity.builder().department(savedD2).name("Pythoneers").build();

        final BusinessUnitEntity savedBu1 = businessUnitRepository.save(bu1);
        final BusinessUnitEntity savedBu2 = businessUnitRepository.save(bu2);
        final BusinessUnitEntity savedBu3 = businessUnitRepository.save(bu3);
        final BusinessUnitEntity savedBu4 = businessUnitRepository.save(bu4);

        businessUnits.add(savedBu1);
        businessUnits.add(savedBu2);
        businessUnits.add(savedBu3);
        businessUnits.add(savedBu4);

        final RegionEntity r1 = RegionEntity.builder().name("Noord").build();
        final RegionEntity r2 = RegionEntity.builder().name("Midden").build();
        final RegionEntity r3 = RegionEntity.builder().name("Zuid").build();

        final RegionEntity savedR1 = regionRepository.save(r1);
        final RegionEntity savedR2 = regionRepository.save(r2);
        final RegionEntity savedR3 = regionRepository.save(r3);

        regions.add(savedR1);
        regions.add(savedR2);
        regions.add(savedR3);

        for(int i = 0; i < 10; i++) {
            Person person = fairy.person();

            final BusinessUnitManagerEntity businessUnitManager =  BusinessUnitManagerEntity.builder()
                    .firstName(person.getFirstName())
                    .prefix(person.getMiddleName())
                    .lastName(person.getLastName())
                    .email(person.getEmail())
                    .businessUnit(businessUnits.get(i%4))
                    .build();

            BusinessUnitManagerEntity savedBusinessUnitManager = businessUnitManagerRepository.save(businessUnitManager);
            businessUnitManagers.add(savedBusinessUnitManager);
        }


        for(int i = 0; i < 100; i++) {

            Person person = fairy.person();

            final ApplicantEntity applicant = ApplicantEntity.builder()
                    .firstName(person.getFirstName())
                    .prefix(person.getMiddleName())
                    .lastName(person.getLastName())
                    .email(person.getEmail())
                    .phoneNumber(person.getTelephoneNumber())
                    .resumeLink("resumelink")
                    .build();

            final ApplicantEntity savedApplicant = applicantRepository.save(applicant);
            applicants.add(savedApplicant);

            final BusinessUnitManagerEntity businessUnitManager = businessUnitManagers.get(i % 10);

            final Random r = new Random();
            ApplicationState randomState = ApplicationState.values()[r.nextInt(ApplicationState.values().length)];

            final ApplicationEntity application = ApplicationEntity.builder()
                    .businessUnit(businessUnitManager.getBusinessUnit())
                    .businessUnitManager(businessUnitManager)
                    .department(businessUnitManager.getBusinessUnit().getDepartment())
                    .region(regions.get(i % 3))
                    .applicant(applicant)
                    .title(randomString((int) (Math.random() * 10 + 10)))
                    .firstInterviewDateTime(ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")).toOffsetDateTime().toString())
                    .secondInterviewDateTime(ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")).toOffsetDateTime().toString())
                    .motivationLetterLink("motivationLetterLink")
                    .state(randomState.name())
                    .build();

            final ApplicationEntity savedApplication = applicationRepository.save(application);
            applications.add(savedApplication);

            for(int j = 0; j < (int)(Math.random()*10); j++) {
                final NoteEntity noteEntity = NoteEntity.builder()
                        .creationDate(ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")).toOffsetDateTime().toString())
                        .title(randomString((int) (Math.random() * 10 + 10)))
                        .text(randomString((int) (Math.random() * 50 + 10)))
                        .author(fairy.person().getFullName())
                        .application(savedApplication)
                        .build();

                final NoteEntity savedNote = noteRepository.save(noteEntity);
                notes.add(savedNote);
            }
        }
    }

    private String randomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
