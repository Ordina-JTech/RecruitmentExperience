package nl.ordina.recruitmentexperience.api.application.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegionModel {
    private Long id;
    private String name;
    private List<ApplicationModel> applications;
}
