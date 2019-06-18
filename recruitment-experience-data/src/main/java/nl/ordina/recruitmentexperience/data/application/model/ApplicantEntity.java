package nl.ordina.recruitmentexperience.data.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Builder
@Entity(name = "applicant")
@Table(name = "applicant")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String resumeLink;
}
