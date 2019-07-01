package nl.ordina.recruitmentexperience.data.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity(name = "document")
@Table(name = "document")
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

    @Id
    private UUID id;
    private String title;
    private String creationDate;
    private String extension;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;
}
