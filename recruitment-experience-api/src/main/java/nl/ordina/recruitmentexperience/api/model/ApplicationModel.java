package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class ApplicationModel {
    private Long id;
    private ApplicantModel applicant;
    private BusinessUnitModel businessUnit;
    private BusinessUnitManagerModel businessUnitManager;
    private RegionModel region;
    private ApplicationStateModel state;
    private String motivationLetterLink;
    private String title;
    private OffsetDateTime firstInterviewDateTime;
    private OffsetDateTime secondInterviewDateTime;
    private DepartmentModel department;
}
