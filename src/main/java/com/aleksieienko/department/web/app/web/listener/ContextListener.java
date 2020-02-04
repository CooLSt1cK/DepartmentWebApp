package com.aleksieienko.department.web.app.web.listener;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.PropertyNames;
import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.db.dao.EmployeeDao;
import com.aleksieienko.department.web.app.db.dao.impl.DepartmentDaoImpl;
import com.aleksieienko.department.web.app.db.dao.impl.EmployeeDaoImpl;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.service.impl.DepartmentServiceImpl;
import com.aleksieienko.department.web.app.service.impl.EmployeeServiceImpl;
import com.aleksieienko.department.web.app.web.AttributeNames;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //do nothing
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (FileInputStream fis = new FileInputStream(Paths.DB_SETTINGS)) {
            ServletContext servletContext = sce.getServletContext();
            PropertyConfigurator.configure(servletContext.getRealPath(Paths.LOG4J_PROPERTIES));
            Properties properties = new Properties();
            properties.load(fis);
            String url = properties.getProperty(PropertyNames.DB_SETTING_URL);
            String user = properties.getProperty(PropertyNames.DB_SETTING_USER);
            String password = properties.getProperty(PropertyNames.DB_SETTING_PASSWORD);
            DBManager dbManager = new DBManager(url, user, password);
            //Department service
            DepartmentDao departmentDao = new DepartmentDaoImpl(dbManager);
            DepartmentService departmentService = new DepartmentServiceImpl(departmentDao);
            List<Department> departmentList = departmentService.getAll();
            LOG.debug("Set attribute to context: " + AttributeNames.DEPARTMENT_LIST + " --> " + departmentList);
            servletContext.setAttribute(AttributeNames.DEPARTMENT_LIST, departmentService.getAll());
            LOG.debug("Set attribute to context: departmentService");
            servletContext.setAttribute(AttributeNames.DEPARTMENT_SERVICE, departmentService);
            //Employee service
            EmployeeDao employeeDao = new EmployeeDaoImpl(dbManager);
            EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);
            LOG.debug("Set attribute to context: employeeService");
            servletContext.setAttribute(AttributeNames.EMPLOYEE_SERVICE, employeeService);
        } catch (IOException e) {
            LOG.error("Opening db_settings error");
        }
    }
}
