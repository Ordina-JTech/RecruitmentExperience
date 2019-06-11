package nl.ordina.recruitmentexperience.core.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
