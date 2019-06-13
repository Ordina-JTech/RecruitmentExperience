package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import nl.ordina.recruitmentexperience.core.model.state.State;

import java.time.OffsetDateTime;

@Getter
@Builder
public class Application {
    private Long id;
    private Applicant applicant;
    private BusinessUnit businessUnit;
    private BusinessUnitManager businessUnitManager;
    private Region region;
    @Setter
    private State state;
    private String motivationLetterLink;
    private String title;
    private OffsetDateTime firstInterviewDateTime;
    private OffsetDateTime secondInterviewDateTime;
    private Department department;
}
