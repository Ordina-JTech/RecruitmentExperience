package nl.ordina.recruitmentexperience.api;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.DepartmentService;
import nl.ordina.recruitmentexperience.data.application.model.DepartmentEntity;
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

    @GetMapping()
    public List<DepartmentEntity> getDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("/{departmentId}")
    public DepartmentEntity getDepartment(@PathVariable final Long departmentId){
        return departmentService.getDepartment(departmentId);
    }
}
