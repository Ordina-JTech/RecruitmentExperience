package nl.ordina.recruitmentexperience.core.application.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.BusinessUnit;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import org.springframework.stereotype.Component;

@Component
public class FromBusinessUnitEntityMapper implements Mapper<BusinessUnitEntity, BusinessUnit> {

    @Override
    public BusinessUnit map(BusinessUnitEntity input) {
        return BusinessUnit.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
