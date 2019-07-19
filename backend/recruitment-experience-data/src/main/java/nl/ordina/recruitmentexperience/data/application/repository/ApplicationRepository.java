package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

    ApplicationEntity findOneById(final Long id);

    List<ApplicationEntity> findAll();

    @Query(value = "SELECT * FROM application WHERE state = ?1 ORDER BY id DESC OFFSET ?2 LIMIT ?3", nativeQuery = true)
    List<ApplicationEntity> findAllByState(final String state, int offset, int size);

    Long countByState(final String state);
}
