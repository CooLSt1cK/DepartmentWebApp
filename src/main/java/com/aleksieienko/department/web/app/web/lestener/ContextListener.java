package com.aleksieienko.department.web.app.web.lestener;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.PropertyNames;
import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.db.dao.EmployeeDao;
import com.aleksieienko.department.web.app.db.dao.impl.DepartmentDaoImpl;
import com.aleksieienko.department.web.app.db.dao.impl.EmployeeDaoImpl;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.service.impl.DepartmentServiceImpl;
import com.aleksieienko.department.web.app.service.impl.EmployeeServiceImpl;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            FileInputStream fis = new FileInputStream(Paths.DB_SETTINGS);
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty(PropertyNames.DB_SETTING_URL);
            String user = properties.getProperty(PropertyNames.DB_SETTING_USER);
            String password = properties.getProperty(PropertyNames.DB_SETTING_PASSWORD);
            DBManager dbManager = new DBManager(url, user, password);
            //Department service
            DepartmentDao departmentDao = new DepartmentDaoImpl(dbManager);
            DepartmentService departmentService = new DepartmentServiceImpl(departmentDao);
            sce.getServletContext().setAttribute(AttributeNames.DEPARTMENT_LIST, departmentDao.getAll());
            sce.getServletContext().setAttribute(AttributeNames.DEPARTMENT_SERVICE, departmentService);
            //Employee service
            EmployeeDao employeeDao = new EmployeeDaoImpl(dbManager);
            EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
            sce.getServletContext().setAttribute(AttributeNames.EMPLOYEE_SERVICE, employeeService);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
