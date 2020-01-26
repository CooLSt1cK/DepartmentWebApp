package com.aleksieienko.department.web.app.service.impl;

import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.service.Patterns;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    @Override
    public Department get(Integer id) {
        return departmentDao.get(id);
    }

    @Override
    public boolean add(String name) {
        Department department = new Department();
        department.setName(name);
        if (department.getName().matches(Patterns.DEPARTMENT_NAME_PATTERN)) {
            return departmentDao.add(department);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Integer id, String name) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        if (department.getName().matches(Patterns.DEPARTMENT_NAME_PATTERN)) {
            return departmentDao.update(department);
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return departmentDao.delete(id);
    }


}
