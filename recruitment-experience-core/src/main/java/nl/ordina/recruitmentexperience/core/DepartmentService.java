package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.core.mapper.FromDepartmentEntityMapper;
import nl.ordina.recruitmentexperience.core.model.Department;
import nl.ordina.recruitmentexperience.data.application.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final FromDepartmentEntityMapper fromDepartmentEntityMapper;

    public List<Department> getDepartments() {
        return fromDepartmentEntityMapper.map(departmentRepository.findAll());
    }

    public Department getDepartment(final Long id) {
        return fromDepartmentEntityMapper.mapNullSafe(departmentRepository.findOneById(id));
    }
}
