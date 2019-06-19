package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter@Builder

public class DocumentId {

    private UUID id;
    private String title;
    private String creationDate;
    private String path;
    private Long applicationId;
}
