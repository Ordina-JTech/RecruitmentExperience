package nl.ordina.recruitmentexperience.core.mapper;

import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Department;
import nl.ordina.recruitmentexperience.data.application.model.DepartmentEntity;
import org.springframework.stereotype.Component;

@Component
public class ToDepartmentEntityMapper implements Mapper<Department, DepartmentEntity> {

    @Override
    public DepartmentEntity map(Department input) {
        return DepartmentEntity.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
