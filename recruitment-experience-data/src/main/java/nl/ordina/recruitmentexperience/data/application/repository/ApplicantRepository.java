package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {

    List<ApplicantEntity> findAll();

    ApplicantEntity findOneById(final Long id);
}
