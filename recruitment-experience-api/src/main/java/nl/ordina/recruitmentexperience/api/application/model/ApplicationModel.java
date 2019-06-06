package nl.ordina.recruitmentexperience.api.application.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApplicationModel {
    private Long id;
    private ApplicantModel applicant;
    private BusinessUnitModel businessUnit;
    private BusinessUnitManagerModel businessUnitManager;
    private List<NoteModel> notes;
    private RegionModel region;
    private ApplicationStateModel applicationState;
    private String motivationLetterLink;
    private LocalDateTime firstInterviewDateTime;
    private LocalDateTime secondInterviewDateTime;
}
