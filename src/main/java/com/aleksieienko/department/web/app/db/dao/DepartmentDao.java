package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.entity.Department;

import java.sql.ResultSet;
import java.util.List;

public interface DepartmentDao {
    List<Department> getAll();

    boolean add(Department department);

    boolean update(Department department);

    boolean delete(Integer id);

    Department mapRow(ResultSet rs);

    Department get(Integer id);
}
