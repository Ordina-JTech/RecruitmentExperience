package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToBusinessUnitModelMapper implements Mapper<BusinessUnit, BusinessUnitModel> {

    private final ToDepartmentModelMapper toDepartmentModelMapper;

    @Override
    public BusinessUnitModel map(BusinessUnit input) {
        final BusinessUnitModel businessUnitModel = new BusinessUnitModel();
        businessUnitModel.setId(input.getId());
        businessUnitModel.setName(input.getName());
        businessUnitModel.setDepartment(toDepartmentModelMapper.map(input.getDepartment()));
        return businessUnitModel;
    }
}
