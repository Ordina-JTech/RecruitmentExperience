package nl.ordina.recruitmentexperience.api.application.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteModel {
    private Long id;
    private String author;
    private String title;
    private String comments;
    private ApplicationModel application;
}
