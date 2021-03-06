package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.ToBusinessUnitManagerModelMapper;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitManagerModel;
import nl.ordina.recruitmentexperience.core.BusinessUnitManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/business-unit-managers")
@RequiredArgsConstructor
public class BusinessUnitManagerRestController {

    private final BusinessUnitManagerService businessUnitManagerService;

    private final ToBusinessUnitManagerModelMapper toBusinessUnitManagerModelMapper;

    @GetMapping
    public List<BusinessUnitManagerModel> getBusinessUnitManagers() {
        return toBusinessUnitManagerModelMapper.map(businessUnitManagerService.getBusinessUnitManagers());
    }

    @GetMapping("/{businessUnitManagerId}")
    public BusinessUnitManagerModel getBusinessUnitManager(@PathVariable final Long businessUnitManagerId){
        return toBusinessUnitManagerModelMapper.mapNullSafe(businessUnitManagerService.getBusinessUnitManager(businessUnitManagerId));
    }
}
