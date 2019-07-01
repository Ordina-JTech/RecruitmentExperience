package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Department;
import nl.ordina.recruitmentexperience.data.application.model.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class FromDepartmentEntityMapper implements Mapper<DepartmentEntity, Department> {

    @Override
    public Department map(DepartmentEntity input) {
        return Department.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
