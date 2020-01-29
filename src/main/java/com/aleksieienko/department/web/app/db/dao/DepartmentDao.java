package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.entity.Department;
import java.util.List;

public interface DepartmentDao {
    List<Department> getAll();

    boolean add(Department department);

    boolean update(Department department);

    boolean deleteById(Integer id);

    Department getById(Integer id);
}
