package com.aleksieienko.department.web.app.service;

import com.aleksieienko.department.web.app.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    Department get(Integer id);

    boolean add(String name);

    boolean update(Integer id, String name);

    boolean delete(Integer id);
}
