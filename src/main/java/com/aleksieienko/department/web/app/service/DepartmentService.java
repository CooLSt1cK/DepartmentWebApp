package com.aleksieienko.department.web.app.service;

import com.aleksieienko.department.web.app.entity.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    Department getById(Integer id);

    boolean add(Department department);

    boolean update(Department department);

    boolean deleteById(Integer id);
}
