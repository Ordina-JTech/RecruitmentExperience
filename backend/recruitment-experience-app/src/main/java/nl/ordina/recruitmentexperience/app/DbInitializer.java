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
import nl.ordina.recruitmentexperience.enums.BusinessUnit;
import nl.ordina.recruitmentexperience.enums.Department;
import nl.ordina.recruitmentexperience.enums.Region;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

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

        saveDepartmentAndBusinessUnit(departments, businessUnits);
        saveRegion(regions);
        saveBusinessUnitManager(businessUnits, businessUnitManagers);
        saveApplicant(applicants,  applications, businessUnitManagers, regions, notes);
    }

    /**
     * This method will generate the random string based on the string length provided
     *
     * @param targetStringLength - length of the target string
     * @return - the Random string
     */
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

    /**
     * This method is used to save the department and Business Entity in the datebase
     *
     * @param departments - List of DepartmentEntity
     * @param businessUnits - List of BusinessUnitEntity
     */
    private void saveDepartmentAndBusinessUnit(List<DepartmentEntity> departments, List<BusinessUnitEntity> businessUnits) {
        final DepartmentEntity savedD1 = departmentRepository.save(DepartmentEntity.
                builder().
                name(Department.OSD.getDepartment()).build());
        final DepartmentEntity savedD2 = departmentRepository.save(DepartmentEntity.
                builder().
                name(Department.TNS.getDepartment()).build());

        departments.add(savedD1);
        departments.add(savedD2);

        final BusinessUnitEntity savedBu1 = businessUnitRepository.save(BusinessUnitEntity.
                builder().
                department(savedD1).
                name(BusinessUnit.JTECH.getBusinessUnit()).build());
        final BusinessUnitEntity savedBu2 = businessUnitRepository.save(BusinessUnitEntity.
                builder().
                department(savedD2).
                name(BusinessUnit.MTECH.getBusinessUnit()).build());
        final BusinessUnitEntity savedBu3 = businessUnitRepository.save(BusinessUnitEntity.
                builder().
                department(savedD1).
                name(BusinessUnit.JROOTS.getBusinessUnit()).build());
        final BusinessUnitEntity savedBu4 = businessUnitRepository.save(BusinessUnitEntity.
                builder().
                department(savedD2).
                name(BusinessUnit.PYTHONEERS.getBusinessUnit()).build());

        businessUnits.add(savedBu1);
        businessUnits.add(savedBu2);
        businessUnits.add(savedBu3);
        businessUnits.add(savedBu4);
    }

    /**
     * This method is used to save the Region entity in the database
     *
     * @param regions - list of RegionEntity
     */
    private void saveRegion(List<RegionEntity> regions){

        final RegionEntity savedR1 = regionRepository.save(RegionEntity.
                builder().
                name(Region.NOORD.getRegion()).build());
        final RegionEntity savedR2 = regionRepository.save(RegionEntity.
                builder().
                name(Region.MIDDEN.getRegion()).build());
        final RegionEntity savedR3 = regionRepository.save(RegionEntity.
                builder().
                name(Region.ZUID.getRegion()).build());

        regions.add(savedR1);
        regions.add(savedR2);
        regions.add(savedR3);
    }

    /**
     * This method is used to save the Business Uint manager details
     *
     * @param businessUnits - list of BusinessUnitEntity
     * @param businessUnitManagers - List of BusinessUnitManagerEntity
     */
    private void saveBusinessUnitManager(List<BusinessUnitEntity> businessUnits, List<BusinessUnitManagerEntity> businessUnitManagers){
        for(int i = 0; i < 10; i++) {
            Person person = fairy.person();
            BusinessUnitManagerEntity savedBusinessUnitManager =
                    businessUnitManagerRepository.save(BusinessUnitManagerEntity.builder()
                    .firstName(person.getFirstName())
                    .prefix(person.getMiddleName())
                    .lastName(person.getLastName())
                    .email(person.getEmail())
                    .businessUnit(businessUnits.get(i%4))
                    .build());
            businessUnitManagers.add(savedBusinessUnitManager);
        }
    }

    /**
     * This method is used to save the Applicant in the database
     *
     * @param applicants - list of ApplicantEntity
     * @param applications - list of ApplicationEntity
     * @param businessUnitManagers - list of BusinessUnitManagerEntity
     * @param regions - list of RegionEntity
     * @param notes - list of NoteEntity
     */
    private void saveApplicant(List<ApplicantEntity> applicants, List<ApplicationEntity> applications,
                                List<BusinessUnitManagerEntity> businessUnitManagers,
                                List<RegionEntity> regions,
                                List<NoteEntity> notes){
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
            saveApplication(i, businessUnitManagers, applicant ,applications, regions, notes);
        }
    }

    /**
     * This method is used to save the application in the database
     *
     * @param i - integer value
     * @param businessUnitManagers - list of BusinessUnitManagerEntity
     * @param applicant - saved ApplicantEntity
     * @param applications - list iof ApplicationEntity
     * @param regions - list of RegionEntity
     * @param notes - list of NoteEntity
     */
    private void saveApplication(int i, List<BusinessUnitManagerEntity> businessUnitManagers,
                                 ApplicantEntity applicant, List<ApplicationEntity> applications,
                                 List<RegionEntity> regions,
                                 List<NoteEntity> notes){
        final BusinessUnitManagerEntity businessUnitManager = businessUnitManagers.get(i % 10);
        final Random r = new Random();
        ApplicationState randomState = ApplicationState.values()[r.nextInt(ApplicationState.values().length)];
        final ApplicationEntity savedApplication = applicationRepository.save(ApplicationEntity.builder()
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
                .build());
        applications.add(savedApplication);
        saveNotes(savedApplication, notes);

    }

    /**
     * This method is used toe save the Notes in the datebase
     *
     * @param savedApplication - saved ApplicationEntity
     * @param notes - list of Notes
     */
    private void saveNotes(ApplicationEntity savedApplication, List<NoteEntity> notes){
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
