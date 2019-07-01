package nl.ordina.recruitmentexperience.core.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToBusinessUnitManagerEntityMapper implements Mapper<BusinessUnitManager, BusinessUnitManagerEntity> {

    private final ToBusinessUnitEntityMapper toBusinessUnitEntityMapper;

    @Override
    public BusinessUnitManagerEntity map(BusinessUnitManager input) {
        return BusinessUnitManagerEntity.builder()
                .id(input.getId())
                .businessUnit(toBusinessUnitEntityMapper.mapNullSafe(input.getBusinessUnit()))
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .build();
    }
}
