package nl.ordina.recruitmentexperience.api.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<ApplicationModel> applications;
}
