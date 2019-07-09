package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessUnitService {

    private final BusinessUnitRepository businessUnitRepository;

    public List<BusinessUnitEntity> getBusinessUnits() {
        return businessUnitRepository.findAll();
    }

    public BusinessUnitEntity getBusinessUnit(final Long id) {
        return businessUnitRepository.findOneById(id);
    }
}
