package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromBusinessUnitManagerEntityMapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnitManager;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessUnitManagerService {

    private final BusinessUnitManagerRepository businessUnitManagerRepository;

    private final FromBusinessUnitManagerEntityMapper fromBusinessUnitManagerEntityMapper;

    public List<BusinessUnitManager> getBusinessUnitManagers() {
        return fromBusinessUnitManagerEntityMapper.map(businessUnitManagerRepository.findAll());
    }

    public BusinessUnitManager getBusinessUnitManager(final Long id) {
        return fromBusinessUnitManagerEntityMapper.mapNullSafe(businessUnitManagerRepository.findOneById(id));
    }
}
