package nl.ordina.recruitmentexperience.core.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Application {
    private Long id;
    private Applicant applicant;
    private BusinessUnit businessUnit;
    private BusinessUnitManager businessUnitManager;
    private Region region;
    private ApplicationState applicationState;
    private String motivationLetterLink;
    private LocalDateTime firstInterviewDateTime;
    private LocalDateTime secondInterviewDateTime;
}
