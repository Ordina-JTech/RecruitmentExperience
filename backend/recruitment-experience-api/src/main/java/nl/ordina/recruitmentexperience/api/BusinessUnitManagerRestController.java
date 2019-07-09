package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.BusinessUnitManagerService;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitManagerEntity;
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

    @GetMapping
    public List<BusinessUnitManagerEntity> getBusinessUnitManagers() {
        return businessUnitManagerService.getBusinessUnitManagers();
    }

    @GetMapping("/{businessUnitManagerId}")
    public BusinessUnitManagerEntity getBusinessUnitManager(@PathVariable final Long businessUnitManagerId){
        return businessUnitManagerService.getBusinessUnitManager(businessUnitManagerId);
    }
}
