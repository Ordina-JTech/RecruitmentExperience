package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class NoteModel {
    private Long id;
    private String author;
    private String title;
    private String text;
    private OffsetDateTime creationDate;
    private ApplicationModel application;
}
