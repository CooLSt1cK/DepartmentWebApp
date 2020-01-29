package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.entity.Employee;
import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();

    Employee getById(Integer id);

    List<Employee> getByDepartmentId(Integer id);

    boolean add(Employee employee);

    boolean update(Employee employee);

    boolean deleteById(Integer id);
}
