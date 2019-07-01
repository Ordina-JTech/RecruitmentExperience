package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;
import nl.ordina.recruitmentexperience.core.model.state.State;

import java.time.OffsetDateTime;

@Getter
@Builder
public class ApplicationId {
    private Long id;
    private Applicant applicant;
    private Long businessUnitId;
    private Long businessUnitManagerId;
    private Long regionId;
    private State state;
    private String motivationLetterLink;
    private String title;
    private OffsetDateTime firstInterviewDateTime;
    private OffsetDateTime secondInterviewDateTime;
    private Long departmentId;
}
