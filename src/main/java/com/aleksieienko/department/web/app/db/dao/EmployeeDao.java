package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.entity.Employee;

import java.sql.ResultSet;
import java.util.List;

public interface EmployeeDao {
    List<Employee> getAll();

    Employee get(Integer id);

    List<Employee> getForDepartment(Integer id);

    boolean add(Employee employee);

    boolean update(Employee employee);

    boolean delete(Integer id);

    Employee mapRow(ResultSet rs);


}
