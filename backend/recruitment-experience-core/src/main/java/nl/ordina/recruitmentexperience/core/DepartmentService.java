package nl.ordina.recruitmentexperience.core;

import lombok.RequiredArgsConstructor;
import nl.ordina.recruitmentexperience.data.application.model.DepartmentEntity;
import nl.ordina.recruitmentexperience.data.application.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;


    public List<DepartmentEntity> getDepartments() {
        return departmentRepository.findAll();
    }

    public DepartmentEntity getDepartment(final Long id) {
        return departmentRepository.findOneById(id);
    }
}
