package nl.ordina.recruitmentexperience.api.application.mapper;

import nl.ordina.recruitmentexperience.api.application.model.ApplicationModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Application;
import org.springframework.stereotype.Component;

@Component
public class ToApplicationModelMapper implements Mapper<Application, ApplicationModel> {

    @Override
    public ApplicationModel map(Application input) {
        final ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setId(input.getId());
        applicationModel.setFirstInterviewDateTime(input.getFirstInterviewDateTime());
        applicationModel.setSecondInterviewDateTime(input.getSecondInterviewDateTime());
        applicationModel.setMotivationLetterLink(input.getMotivationLetterLink());

        return applicationModel;
    }
}
