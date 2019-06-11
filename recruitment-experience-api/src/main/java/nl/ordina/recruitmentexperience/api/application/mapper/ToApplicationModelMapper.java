package nl.ordina.recruitmentexperience.api.application.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.application.model.ApplicationModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Application;
import nl.ordina.recruitmentexperience.core.application.model.ApplicationState;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToApplicationModelMapper implements Mapper<Application, ApplicationModel> {

    private final ToApplicantModelMapper toApplicantModelMapper;

    @Override
    public ApplicationModel map(Application input) {
        final ApplicationModel applicationModel = new ApplicationModel();
        applicationModel.setId(input.getId());
        applicationModel.setFirstInterviewDateTime(input.getFirstInterviewDateTime());
        applicationModel.setSecondInterviewDateTime(input.getSecondInterviewDateTime());
        applicationModel.setMotivationLetterLink(input.getMotivationLetterLink());
        applicationModel.setApplicant(toApplicantModelMapper.map(input.getApplicant()));
        applicationModel.setApplicationState((new ToApplicationStateModelMapper(ApplicationState.class)).get(input.getApplicationState()));
        applicationModel.setBusinessUnitId(input.getBusinessUnit().getId());
        applicationModel.setBusinessUnitManagerId(input.getBusinessUnitManager().getId());
        applicationModel.setRegionId(input.getRegion().getId());
        return applicationModel;
    }
}
