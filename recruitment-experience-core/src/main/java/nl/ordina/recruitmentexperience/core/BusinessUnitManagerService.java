package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromBusinessUnitEntityMapper;
import nl.ordina.recruitmentexperience.core.mapper.FromBusinessUnitManagerEntityMapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessUnitManagerService {

    private final BusinessUnitManagerRepository businessUnitManagerRepository;

    private final FromBusinessUnitManagerEntityMapper fromBusinessUnitManagerEntityMapper;

    public BusinessUnitManager getBusinessUnitManager(final Long id) {
        return fromBusinessUnitManagerEntityMapper.mapNullSafe(businessUnitManagerRepository.findOneById(id));
    }
}
