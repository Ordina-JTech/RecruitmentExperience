package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToApplicationEntityMapper implements Mapper<Application, ApplicationEntity> {

    private final ToDepartmentEntityMapper toDepartmentEntityMapper;

    private final ToBusinessUnitManagerEntityMapper toBusinessUnitManagerEntityMapper;

    private final ToBusinessUnitEntityMapper toBusinessUnitEntityMapper;

    private final ToRegionEntityMapper toRegionEntityMapper;

    @Override
    public ApplicationEntity map(Application input) {
        return ApplicationEntity.builder()
                .id(input.getId())
                .title(input.getTitle())
                .firstInterviewDateTime(input.getFirstInterviewDateTime().toString())
                .secondInterviewDateTime(input.getSecondInterviewDateTime().toString())
                .motivationLetterLink(input.getMotivationLetterLink())
                .department(toDepartmentEntityMapper.mapNullSafe(input.getDepartment()))
                .businessUnitManager(toBusinessUnitManagerEntityMapper.mapNullSafe(input.getBusinessUnitManager()))
                .businessUnit(toBusinessUnitEntityMapper.mapNullSafe(input.getBusinessUnit()))
                .region(toRegionEntityMapper.mapNullSafe(input.getRegion()))
                .build();
    }
}
