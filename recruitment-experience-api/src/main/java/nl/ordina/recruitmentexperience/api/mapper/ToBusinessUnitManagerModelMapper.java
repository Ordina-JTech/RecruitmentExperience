package nl.ordina.recruitmentexperience.api.mapper;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitManagerModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToBusinessUnitManagerModelMapper implements Mapper<BusinessUnitManager, BusinessUnitManagerModel> {

    private final ToBusinessUnitModelMapper toBusinessUnitModelMapper;

    @Override
    public BusinessUnitManagerModel map(BusinessUnitManager input) {
        final BusinessUnitManagerModel businessUnitManagerModel = new BusinessUnitManagerModel();
        businessUnitManagerModel.setId(input.getId());
        businessUnitManagerModel.setBusinessUnit(toBusinessUnitModelMapper.map(input.getBusinessUnit()));
        businessUnitManagerModel.setEmail(input.getEmail());
        businessUnitManagerModel.setFirstName(input.getFirstName());
        businessUnitManagerModel.setPrefix(input.getPrefix());
        businessUnitManagerModel.setLastName(input.getLastName());
        return businessUnitManagerModel;
    }
}
