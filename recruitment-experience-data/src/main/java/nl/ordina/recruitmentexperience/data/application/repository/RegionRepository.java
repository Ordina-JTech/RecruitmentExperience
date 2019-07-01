package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<RegionEntity, Long> {

    List<RegionEntity> findAll();

    RegionEntity findOneById(final Long id);
}
