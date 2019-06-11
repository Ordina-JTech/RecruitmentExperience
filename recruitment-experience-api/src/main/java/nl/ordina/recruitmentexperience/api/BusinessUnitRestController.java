package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.FromApplicationStateModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToApplicationModelMapper;
import nl.ordina.recruitmentexperience.api.mapper.ToBusinessUnitModelMapper;
import nl.ordina.recruitmentexperience.api.model.ApplicationModel;
import nl.ordina.recruitmentexperience.api.model.ApplicationStateModel;
import nl.ordina.recruitmentexperience.api.model.BusinessUnitModel;
import nl.ordina.recruitmentexperience.core.ApplicationService;
import nl.ordina.recruitmentexperience.core.BusinessUnitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/business-units")
@RequiredArgsConstructor
public class BusinessUnitRestController {

    private final BusinessUnitService businessUnitService;

    private final ToBusinessUnitModelMapper toBusinessUnitModelMapper;

    @GetMapping("/{businessUnitId}")
    public BusinessUnitModel getApplication(@PathVariable final Long businessUnitId){
        return toBusinessUnitModelMapper.mapNullSafe(businessUnitService.getBusinessUnit(businessUnitId));
    }
}
