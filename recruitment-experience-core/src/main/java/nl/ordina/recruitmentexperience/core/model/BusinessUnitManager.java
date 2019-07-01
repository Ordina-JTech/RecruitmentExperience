package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessUnitManager {
    private Long id;
    private String firstName;
    private String prefix;
    private String lastName;
    private String email;
    private BusinessUnit businessUnit;
}
