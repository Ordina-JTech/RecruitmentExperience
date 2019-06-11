package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessUnitManagerRepository extends JpaRepository<BusinessUnitManagerEntity, Long> {

    List<BusinessUnitManagerEntity> findAll();
}
