package nl.ordina.recruitmentexperience.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessUnitModel {
    private Long id;
    private String name;
    private DepartmentModel department;
}
