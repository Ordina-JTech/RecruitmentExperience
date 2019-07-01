package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromRegionEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Region;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    private final FromRegionEntityMapper fromRegionEntityMapper;

    public List<Region> getRegions() {
        return fromRegionEntityMapper.map(regionRepository.findAll());
    }

    public Region getRegion(final Long id) {
        return fromRegionEntityMapper.mapNullSafe(regionRepository.findOneById(id));
    }
}
