package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.DepartmentModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Department;
import org.springframework.stereotype.Component;

@Component
public class FromDepartmentModelMapper implements Mapper<DepartmentModel, Department> {

    @Override
    public Department map(DepartmentModel input) {
        return Department.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
