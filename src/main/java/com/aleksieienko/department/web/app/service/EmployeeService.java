package com.aleksieienko.department.web.app.service;

import com.aleksieienko.department.web.app.entity.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee get(Integer id);

    List<Employee> getForDepartment(Integer id);

    boolean add(String email, String name, LocalDate birthday, Integer payment, Integer departmentId);

    boolean update(Integer id, String email, String name,
                   LocalDate birthday, Integer payment, Integer departmentId);

    boolean delete(Integer id);
}
