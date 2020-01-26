package com.aleksieienko.department.web.app.service.impl;

import com.aleksieienko.department.web.app.db.dao.EmployeeDao;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.service.Patterns;

import java.time.LocalDate;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAll() {
        return employeeDao.getAll();
    }

    @Override
    public Employee get(Integer id) {
        return employeeDao.get(id);
    }

    @Override
    public List<Employee> getForDepartment(Integer id) {
        return employeeDao.getForDepartment(id);
    }

    @Override
    public boolean add(String email, String name,
                       LocalDate birthday, Integer payment, Integer departmentId) {
        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);
        if (employee.getEmail().matches(Patterns.EMPLOYEE_EMAIL_PATTERN)
                && employee.getName().matches(Patterns.EMPLOYEE_NAME_PATTERN)
                && employee.getEmail().length() <= 255
                && employee.getBirthday().isBefore(LocalDate.now())) {
            return employeeDao.add(employee);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Integer id, String email, String name,
                          LocalDate birthday, Integer payment, Integer departmentId) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);
        if (employee.getEmail().matches(Patterns.EMPLOYEE_EMAIL_PATTERN)
                && employee.getName().matches(Patterns.EMPLOYEE_NAME_PATTERN)
                && employee.getEmail().length() <= 255
                && employee.getBirthday().isBefore(LocalDate.now())) {
            return employeeDao.update(employee);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDao.delete(id);
    }
}
