package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.ApplicationModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromApplicationModelMapper implements Mapper<ApplicationModel, Application> {

    private final FromApplicantModelMapper fromApplicantModelMapper;

    private final FromBusinessUnitModelMapper fromBusinessUnitModelMapper;

    private final FromBusinessUnitManagerModelMapper fromBusinessUnitManagerModelMapper;

    private final FromDepartmentModelMapper fromDepartmentModelMapper;

    private final FromRegionModelMapper fromRegionModelMapper;

    @Override
    public Application map(ApplicationModel input) {
        return Application.builder()
                .id(input.getId())
                .applicant(fromApplicantModelMapper.mapNullSafe(input.getApplicant()))
                .title(input.getTitle())
                .state((new FromApplicationStateModelMapper(ApplicationStateModel.class)).get(input.getState()))
                .department(fromDepartmentModelMapper.mapNullSafe(input.getDepartment()))
                .businessUnit(fromBusinessUnitModelMapper.mapNullSafe(input.getBusinessUnit()))
                .businessUnitManager(fromBusinessUnitManagerModelMapper.mapNullSafe(input.getBusinessUnitManager()))
                .region(fromRegionModelMapper.mapNullSafe(input.getRegion()))
                .firstInterviewDateTime(input.getFirstInterviewDateTime())
                .secondInterviewDateTime(input.getSecondInterviewDateTime())
                .motivationLetterLink(input.getMotivationLetterLink())
                .build();
    }
}
