package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.RegionModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Region;
import org.springframework.stereotype.Component;

@Component
public class ToRegionModelMapper implements Mapper<Region, RegionModel> {

    @Override
    public RegionModel map(Region input) {
        final RegionModel regionModel = new RegionModel();
        regionModel.setId(input.getId());
        regionModel.setName(input.getName());
        return regionModel;
    }
}
