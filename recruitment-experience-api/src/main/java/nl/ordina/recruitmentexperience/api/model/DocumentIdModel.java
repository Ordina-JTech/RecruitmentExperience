package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DocumentIdModel {

    private UUID id;
    private String title;
    private String creationDate;
    private String path;
    private String author;
    private Long applicationId;

}
