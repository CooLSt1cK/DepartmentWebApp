package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import com.aleksieienko.department.web.app.web.ParameterNames;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private DepartmentService departmentService;

    private static final Logger LOG = Logger.getLogger(EmployeeServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentService = (DepartmentService) config.getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);
        employeeService = (EmployeeService) config.getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter(ParameterNames.ID));
        List<Employee> list = employeeService.getByDepartmentId(id);
        LOG.debug("Got employees' list from database: list --> " + list);
        Department department = departmentService.getById(id);
        LOG.debug("Got department from database by id: department --> " + department);
        request.setAttribute(AttributeNames.DEPARTMENT_BY_ID, department);
        request.setAttribute(AttributeNames.EMPLOYEE_LIST, list);
        request.getRequestDispatcher(Paths.EMPLOYEE_JSP).forward(request, response);
    }

}
