package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.ToBusinessUnitManagerModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToBusinessUnitModelMapper;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitManagerModel;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitModel;
import nl.ordina.recruitmentexperience.core.BusinessUnitManagerService;
import nl.ordina.recruitmentexperience.core.BusinessUnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/business-unit-managers")
@RequiredArgsConstructor
public class BusinessUnitManagerRestController {

    private final BusinessUnitManagerService businessUnitManagerService;

    private final ToBusinessUnitManagerModelMapper toBusinessUnitManagerModelMapper;

    @GetMapping("/{businessUnitManagerId}")
    public BusinessUnitManagerModel getApplication(@PathVariable final Long businessUnitManagerId){
        return toBusinessUnitManagerModelMapper.mapNullSafe(businessUnitManagerService.getBusinessUnitManager(businessUnitManagerId));
    }
}
