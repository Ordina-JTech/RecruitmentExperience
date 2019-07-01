package nl.ordina.recruitmentexperience.api.mapper;

import nl.ordina.recruitmentexperience.api.model.DepartmentModel;
import nl.ordina.recruitmentexperience.common.Mapper;
import nl.ordina.recruitmentexperience.core.model.Department;
import org.springframework.stereotype.Component;

@Component
public class ToDepartmentModelMapper implements Mapper<Department, DepartmentModel> {

    @Override
    public DepartmentModel map(Department input) {
        final DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId(input.getId());
        departmentModel.setName(input.getName());
        return departmentModel;
    }
}
