package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BusinessUnit {
    private Long id;
    private String name;
    private Department department;
}
