package com.aleksieienko.department.web.app.service.impl;

import com.aleksieienko.department.web.app.db.dao.EmployeeDao;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
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
    public Employee getById(Integer id) {
        return employeeDao.getById(id);
    }

    @Override
    public List<Employee> getByDepartmentId(Integer id) {
        return employeeDao.getByDepartmentId(id);
    }

    @Override
    public boolean add(Employee employee) {
        if (employee.isValid()) {
            return employeeDao.add(employee);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Employee employee) {
        if (employee.isValid()) {
            return employeeDao.update(employee);
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return employeeDao.deleteById(id);
    }
}
