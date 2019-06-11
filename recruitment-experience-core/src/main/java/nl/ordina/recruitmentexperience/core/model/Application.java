package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Application {
    private Long id;
    private Applicant applicant;
    private BusinessUnit businessUnit;
    private BusinessUnitManager businessUnitManager;
    private Region region;
    private ApplicationState state;
    private String motivationLetterLink;
    private LocalDateTime firstInterviewDateTime;
    private LocalDateTime secondInterviewDateTime;
}
