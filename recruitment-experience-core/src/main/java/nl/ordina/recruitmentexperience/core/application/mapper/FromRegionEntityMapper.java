package nl.ordina.recruitmentexperience.core.application.mapper;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.application.model.Region;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import org.springframework.stereotype.Component;

@Component
public class FromRegionEntityMapper implements Mapper<RegionEntity, Region> {

    @Override
    public Region map(RegionEntity input) {
        return Region.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
