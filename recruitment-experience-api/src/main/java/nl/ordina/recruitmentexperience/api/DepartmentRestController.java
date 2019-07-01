package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.api.mapper.ToDepartmentModelMapper;
import nl.ordina.recruitmentexperience.api.model.DepartmentModel;
import nl.ordina.recruitmentexperience.core.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentRestController {

    private final DepartmentService departmentService;

    private final ToDepartmentModelMapper toDepartmentModelMapper;

    @GetMapping()
    public List<DepartmentModel> getDepartments() {
        return toDepartmentModelMapper.map(departmentService.getDepartments());
    }

    @GetMapping("/{departmentId}")
    public DepartmentModel getDepartment(@PathVariable final Long departmentId){
        return toDepartmentModelMapper.mapNullSafe(departmentService.getDepartment(departmentId));
    }
}
