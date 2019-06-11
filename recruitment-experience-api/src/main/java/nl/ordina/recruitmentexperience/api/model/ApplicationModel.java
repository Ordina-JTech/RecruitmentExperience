package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationModel {
    private Long id;
    private ApplicantModel applicant;
    private Long businessUnitId;
    private Long businessUnitManagerId;
    private Long regionId;
    private ApplicationStateModel state;
    private String motivationLetterLink;
    private LocalDateTime firstInterviewDateTime;
    private LocalDateTime secondInterviewDateTime;
}
