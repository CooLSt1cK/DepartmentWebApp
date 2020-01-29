package com.aleksieienko.department.web.app.service.impl;

import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
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
    public Department getById(Integer id) {
        return departmentDao.getById(id);
    }

    @Override
    public boolean add(Department department) {
        if (department.isValid()) {
            return departmentDao.add(department);
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Department department) {
        if (department.isValid()) {
            return departmentDao.update(department);
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        return departmentDao.deleteById(id);
    }


}
