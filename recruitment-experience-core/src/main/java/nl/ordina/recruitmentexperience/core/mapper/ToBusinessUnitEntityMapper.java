package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToBusinessUnitEntityMapper implements Mapper<BusinessUnit, BusinessUnitEntity> {

    private final ToDepartmentEntityMapper toDepartmentEntityMapper;

    @Override
    public BusinessUnitEntity map(BusinessUnit input) {
        return BusinessUnitEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .department(toDepartmentEntityMapper.mapNullSafe(input.getDepartment()))
                .build();
    }
}
