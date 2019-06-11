package nl.ordina.recruitmentexperience.core.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class Region {
    private Long id;
    private String name;
}
