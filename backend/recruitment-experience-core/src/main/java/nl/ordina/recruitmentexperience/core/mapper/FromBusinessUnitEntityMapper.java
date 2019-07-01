package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromBusinessUnitEntityMapper implements Mapper<BusinessUnitEntity, BusinessUnit> {

    private final FromDepartmentEntityMapper fromDepartmentEntityMapper;

    @Override
    public BusinessUnit map(BusinessUnitEntity input) {
        return BusinessUnit.builder()
                .id(input.getId())
                .name(input.getName())
                .department(fromDepartmentEntityMapper.map(input.getDepartment()))
                .build();
    }
}
