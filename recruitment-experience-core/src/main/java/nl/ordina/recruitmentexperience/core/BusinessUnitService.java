package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromApplicationEntityMapper;
import nl.ordina.recruitmentexperience.core.mapper.FromBusinessUnitEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Application;
import nl.ordina.recruitmentexperience.core.model.ApplicationState;
import nl.ordina.recruitmentexperience.core.model.BusinessUnit;
import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import nl.ordina.recruitmentexperience.data.application.repository.ApplicationRepository;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;

    private final FromBusinessUnitEntityMapper fromBusinessUnitEntityMapper;

    public BusinessUnit getBusinessUnit(final Long id) {
        return fromBusinessUnitEntityMapper.mapNullSafe(businessUnitRepository.findOneById(id));
    }
}
