package nl.ordina.recruitmentexperience.core.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Region {
    private Long id;
    private String name;
}
