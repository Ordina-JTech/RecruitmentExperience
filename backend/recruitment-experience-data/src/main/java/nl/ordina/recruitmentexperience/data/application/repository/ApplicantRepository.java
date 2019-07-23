package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.ApplicantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicantRepository extends JpaRepository<ApplicantEntity, Long> {

    List<ApplicantEntity> findAll();

    ApplicantEntity findOneById(final Long id);

    @Query(value = "SELECT a.id FROM applicant a WHERE " +
            "a.first_name ILIKE CONCAT('%', :query,'%') OR a.last_name ILIKE CONCAT('%', :query,'%')"
            ,nativeQuery = true)
    List<Long> searchApplicants(String query);
}
