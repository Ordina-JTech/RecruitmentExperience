package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToApplicationIdModelMapper implements Mapper<Application, ApplicationIdModel> {

    private final ToApplicantModelMapper toApplicantModelMapper;

    @Override
    public ApplicationIdModel map(Application input) {
        final ApplicationIdModel applicationIdModel = new ApplicationIdModel();
        applicationIdModel.setId(input.getId());
        applicationIdModel.setFirstInterviewDateTime(input.getFirstInterviewDateTime());
        applicationIdModel.setSecondInterviewDateTime(input.getSecondInterviewDateTime());
        applicationIdModel.setMotivationLetterLink(input.getMotivationLetterLink());
        applicationIdModel.setApplicant(toApplicantModelMapper.map(input.getApplicant()));
        applicationIdModel.setState((new ToApplicationStateModelMapper(ApplicationState.class)).get(input.getState().toEnum()));
        applicationIdModel.setBusinessUnitId(input.getBusinessUnit().getId());
        applicationIdModel.setBusinessUnitManagerId(input.getBusinessUnitManager().getId());
        applicationIdModel.setRegionId(input.getRegion().getId());
        applicationIdModel.setTitle(input.getTitle());
        applicationIdModel.setDepartmentId(input.getDepartment().getId());
        return applicationIdModel;
    }
}
