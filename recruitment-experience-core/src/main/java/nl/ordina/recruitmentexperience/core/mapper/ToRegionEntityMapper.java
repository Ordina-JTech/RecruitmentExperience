package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Region;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import org.springframework.stereotype.Component;

@Component
public class ToRegionEntityMapper implements Mapper<Region, RegionEntity> {

    @Override
    public RegionEntity map(Region input) {
        return RegionEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
