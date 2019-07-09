package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.RegionService;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
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

    @GetMapping()
    public List<RegionEntity> getRegions() {
        return regionService.getRegions();
    }

    @GetMapping("/{regionId}")
    public RegionEntity getRegion(@PathVariable final Long regionId){
        return regionService.getRegion(regionId);
    }
}
