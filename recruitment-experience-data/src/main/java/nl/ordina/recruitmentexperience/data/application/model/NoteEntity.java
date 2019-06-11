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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String text;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;
}
