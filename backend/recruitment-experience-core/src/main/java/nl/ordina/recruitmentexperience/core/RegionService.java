package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.RegionEntity;
import nl.ordina.recruitmentexperience.data.application.repository.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public List<RegionEntity> getRegions() {
        return regionRepository.findAll();
    }

    public RegionEntity getRegion(final Long id) {
        return regionRepository.findOneById(id);
    }
}
