package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.ApplicationState;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromApplicationEntityMapper implements Mapper<ApplicationEntity, Application> {

    private final FromRegionEntityMapper fromRegionEntityMapper;

    private final FromApplicantEntityMapper fromApplicantEntityMapper;

    private final FromBusinessUnitEntityMapper fromBusinessUnitEntityMapper;

    private final FromBusinessUnitManagerEntityMapper fromBusinessUnitManagerEntityMapper;

    @Override
    public Application map(ApplicationEntity input) {
        return Application.builder()
                .id(input.getId())
                .region(fromRegionEntityMapper.map(input.getRegion()))
                .motivationLetterLink(input.getMotivationLetterLink())
                .firstInterviewDateTime(input.getFirstInterviewDateTime())
                .secondInterviewDateTime(input.getSecondInterviewDateTime())
                .applicant(fromApplicantEntityMapper.map(input.getApplicant()))
                .state(ApplicationState.valueOf(input.getState()))
                .businessUnit(fromBusinessUnitEntityMapper.map(input.getBusinessUnit()))
                .businessUnitManager(fromBusinessUnitManagerEntityMapper.map(input.getBusinessUnitManager()))
                .build();
    }
}
