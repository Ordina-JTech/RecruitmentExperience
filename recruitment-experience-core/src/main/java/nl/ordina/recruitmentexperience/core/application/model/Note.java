package nl.ordina.recruitmentexperience.core.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Builder

public class Note {
    private Long id;
    private String author;
    private String title;
    private String comments;
    private Application application;
}
