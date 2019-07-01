package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.ToRegionModelMapper;
import nl.ordina.recruitmentexperience.api.model.RegionModel;
import nl.ordina.recruitmentexperience.core.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class RegionRestController {

    private final RegionService regionService;

    private final ToRegionModelMapper toRegionModelMapper;

    @GetMapping()
    public List<RegionModel> getRegions() {
        return toRegionModelMapper.map(regionService.getRegions());
    }

    @GetMapping("/{regionId}")
    public RegionModel getRegion(@PathVariable final Long regionId){
        return toRegionModelMapper.mapNullSafe(regionService.getRegion(regionId));
    }
}
