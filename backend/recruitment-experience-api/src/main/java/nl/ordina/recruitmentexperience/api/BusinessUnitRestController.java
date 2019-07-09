package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.BusinessUnitService;
import nl.ordina.recruitmentexperience.data.application.model.BusinessUnitEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/business-units")
@RequiredArgsConstructor
public class BusinessUnitRestController {

    private final BusinessUnitService businessUnitService;

    @GetMapping
    public List<BusinessUnitEntity> getBusinessUnits() {
        return businessUnitService.getBusinessUnits();
    }

    @GetMapping("/{businessUnitId}")
    public BusinessUnitEntity getBusinessUnit(@PathVariable final Long businessUnitId){
        return businessUnitService.getBusinessUnit(businessUnitId);
    }
}
