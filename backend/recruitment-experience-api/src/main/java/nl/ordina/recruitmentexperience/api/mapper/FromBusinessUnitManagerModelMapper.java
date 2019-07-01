package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitManagerModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FromBusinessUnitManagerModelMapper implements Mapper<BusinessUnitManagerModel, BusinessUnitManager> {

    private final FromBusinessUnitModelMapper fromBusinessUnitModelMapper;

    @Override
    public BusinessUnitManager map(BusinessUnitManagerModel input) {
        return BusinessUnitManager.builder()
                .id(input.getId())
                .businessUnit(fromBusinessUnitModelMapper.mapNullSafe(input.getBusinessUnit()))
                .email(input.getEmail())
                .firstName(input.getFirstName())
                .prefix(input.getPrefix())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .build();
    }
}
