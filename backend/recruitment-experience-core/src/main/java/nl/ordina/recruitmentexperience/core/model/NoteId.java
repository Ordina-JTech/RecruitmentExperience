package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Builder
public class NoteId {

    private Long id;
    private String author;
    private String title;
    private String text;
    private Long applicationId;
    private OffsetDateTime creationDate;
}
