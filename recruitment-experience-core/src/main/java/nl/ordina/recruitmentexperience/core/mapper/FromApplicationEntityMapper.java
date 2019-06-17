package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.state.ApplicationState;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@RequiredArgsConstructor
public class FromApplicationEntityMapper implements Mapper<ApplicationEntity, Application> {

    private final FromRegionEntityMapper fromRegionEntityMapper;

    private final FromApplicantEntityMapper fromApplicantEntityMapper;

    private final FromBusinessUnitEntityMapper fromBusinessUnitEntityMapper;

    private final FromBusinessUnitManagerEntityMapper fromBusinessUnitManagerEntityMapper;

    private final FromDepartmentEntityMapper fromDepartmentEntityMapper;

    private final ApplicationStateToStateMapper applicationStateToStateMapper;

    @Override
    public Application map(ApplicationEntity input) {
        final ApplicationState applicationState = ApplicationState.valueOf(input.getState());

        final String firstInterviewDateTime = input.getFirstInterviewDateTime();
        final OffsetDateTime  firstInterview = firstInterviewDateTime == null ? null : OffsetDateTime.parse(firstInterviewDateTime);
        final String secondInterviewDateTime = input.getSecondInterviewDateTime();
        final OffsetDateTime  secondInterview = secondInterviewDateTime == null ? null : OffsetDateTime.parse(secondInterviewDateTime);

        return Application.builder()
                .id(input.getId())
                .region(fromRegionEntityMapper.map(input.getRegion()))
                .motivationLetterLink(input.getMotivationLetterLink())
                .firstInterviewDateTime(firstInterview)
                .secondInterviewDateTime(secondInterview)
                .applicant(fromApplicantEntityMapper.map(input.getApplicant()))
                .state(applicationStateToStateMapper.map(applicationState))
                .businessUnit(fromBusinessUnitEntityMapper.map(input.getBusinessUnit()))
                .businessUnitManager(fromBusinessUnitManagerEntityMapper.map(input.getBusinessUnitManager()))
                .title(input.getTitle())
                .department(fromDepartmentEntityMapper.map(input.getDepartment()))
                .build();
    }
}
