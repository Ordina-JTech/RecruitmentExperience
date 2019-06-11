package nl.ordina.recruitmentexperience.core.application;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Application;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
public class FromApplicationEntityMapper implements Mapper<ApplicationEntity, Application> {
    @Override
    public Application map(ApplicationEntity input) {
        return Application.builder()
                .id(input.getId())
                .firstInterviewDateTime(input.getFirstInterviewDateTime())
                .secondInterviewDateTime(input.getSecondInterviewDateTime())
                .motivationLetterLink(input.getMotivationLetterLink())
                .build();
    }
}
