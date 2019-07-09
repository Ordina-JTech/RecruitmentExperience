package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import nl.ordina.recruitmentexperience.data.application.repository.BusinessUnitManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusinessUnitManagerService {

    private final BusinessUnitManagerRepository businessUnitManagerRepository;

    public List<BusinessUnitManagerEntity> getBusinessUnitManagers() {
        return businessUnitManagerRepository.findAll();
    }

    public BusinessUnitManagerEntity getBusinessUnitManager(final Long id) {
        return businessUnitManagerRepository.findOneById(id);
    }
}
