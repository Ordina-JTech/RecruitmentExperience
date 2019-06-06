package nl.ordina.recruitmentexperience.data.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@Entity(name = "applicant")
@Table(name = "applicant")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String resumeLink;
    @OneToMany(
            mappedBy = "applicant",
            cascade = CascadeType.ALL
    )
    private List<ApplicationEntity> applications;
}
