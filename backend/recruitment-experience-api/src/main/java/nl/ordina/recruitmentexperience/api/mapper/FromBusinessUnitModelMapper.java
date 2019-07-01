package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromBusinessUnitModelMapper implements Mapper<BusinessUnitModel, BusinessUnit> {

    private final FromDepartmentModelMapper fromDepartmentModelMapper;

    @Override
    public BusinessUnit map(BusinessUnitModel input) {
        return BusinessUnit.builder()
                .id(input.getId())
                .name(input.getName())
                .department(fromDepartmentModelMapper.mapNullSafe(input.getDepartment()))
                .build();
    }
}
