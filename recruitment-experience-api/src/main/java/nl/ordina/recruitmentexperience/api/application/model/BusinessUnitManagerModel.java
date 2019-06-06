package nl.ordina.recruitmentexperience.api.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessUnitManagerModel {
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private List<ApplicationModel> applications;
    private BusinessUnitModel businessUnit;
}
