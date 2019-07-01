package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantModel {
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String resumeLink;
}
