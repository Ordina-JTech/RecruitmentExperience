package nl.ordina.recruitmentexperience.data.application.repository;

import nl.ordina.recruitmentexperience.data.application.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, UUID> {

    DocumentEntity findOneById(final UUID uuid);

    List<DocumentEntity> findAll();

    List<DocumentEntity> findAllByApplication_Id(final Long applicationId);
}
