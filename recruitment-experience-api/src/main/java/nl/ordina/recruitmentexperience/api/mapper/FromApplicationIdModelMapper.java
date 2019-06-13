package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.ApplicationIdModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.ApplicationId;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromApplicationIdModelMapper implements Mapper<ApplicationIdModel, ApplicationId> {

    private final FromApplicantModelMapper fromApplicantModelMapper;

    @Override
    public ApplicationId map(ApplicationIdModel input) {
        return ApplicationId.builder()
                .id(input.getId())
                .applicant(fromApplicantModelMapper.mapNullSafe(input.getApplicant()))
                .title(input.getTitle())
                .state((new FromApplicationStateModelMapper(ApplicationStateModel.class)).get(input.getState()))
                .departmentId(input.getDepartmentId())
                .regionId(input.getRegionId())
                .businessUnitId(input.getBusinessUnitId())
                .businessUnitManagerId(input.getBusinessUnitManagerId())
                .firstInterviewDateTime(input.getFirstInterviewDateTime())
                .secondInterviewDateTime(input.getSecondInterviewDateTime())
                .motivationLetterLink(input.getMotivationLetterLink())
                .build();
    }
}
