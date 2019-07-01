package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromBusinessUnitManagerEntityMapper implements Mapper<BusinessUnitManagerEntity, BusinessUnitManager> {

    private final FromBusinessUnitEntityMapper fromBusinessUnitEntityMapper;

    @Override
    public BusinessUnitManager map(BusinessUnitManagerEntity input) {
        return BusinessUnitManager.builder()
                .id(input.getId())
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .businessUnit(fromBusinessUnitEntityMapper.map(input.getBusinessUnit()))
                .build();
    }
}
