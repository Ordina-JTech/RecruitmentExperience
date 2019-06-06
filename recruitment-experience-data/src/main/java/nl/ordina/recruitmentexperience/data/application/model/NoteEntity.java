package nl.ordina.recruitmentexperience.data.application.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity(name = "note")
@Table(name = "note")
@NoArgsConstructor
@AllArgsConstructor
public class NoteEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String author;
    private String title;
    private String comments;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;
}
