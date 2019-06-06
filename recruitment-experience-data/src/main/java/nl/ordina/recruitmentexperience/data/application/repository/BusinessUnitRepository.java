package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessUnitRepository extends CrudRepository<BusinessUnitEntity, Long> {
}
