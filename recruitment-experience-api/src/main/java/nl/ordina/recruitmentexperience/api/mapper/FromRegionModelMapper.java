package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.RegionModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Region;
import org.springframework.stereotype.Component;

@Component
public class FromRegionModelMapper implements Mapper<RegionModel, Region> {

    @Override
    public Region map(RegionModel input) {
        return Region.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
