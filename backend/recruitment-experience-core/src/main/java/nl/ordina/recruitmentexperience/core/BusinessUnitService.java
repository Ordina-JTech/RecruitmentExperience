package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromBusinessUnitEntityMapper;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;

    private final FromBusinessUnitEntityMapper fromBusinessUnitEntityMapper;

    public List<BusinessUnit> getBusinessUnits() {
        return fromBusinessUnitEntityMapper.map(businessUnitRepository.findAll());
    }

    public BusinessUnit getBusinessUnit(final Long id) {
        return fromBusinessUnitEntityMapper.mapNullSafe(businessUnitRepository.findOneById(id));
    }
}
