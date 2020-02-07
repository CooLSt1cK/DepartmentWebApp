package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import com.aleksieienko.department.web.app.web.ParameterNames;
import com.aleksieienko.department.web.app.web.servlet.ErrorMessages;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aleksieienko.department.web.app.web.servlet.ParameterPatterns;
import org.apache.log4j.Logger;

@WebServlet(name = "UpdateEmployeeServlet", value = "/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    private static final Logger LOG = Logger.getLogger(UpdateEmployeeServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeService) config.getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        Employee employee = employeeService.getById(id);
        LOG.debug("Got employee by id: employee --> " + employee);
        req.setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
        req.setAttribute(AttributeNames.OLDER_THEN, LocalDate.now().minusYears(18));
        req.setAttribute(AttributeNames.EMPLOYEE_DEPARTMENT_ID, employee.getDepartmentId());
        req.getRequestDispatcher(Paths.UPDATE_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = (req.getParameter(ParameterNames.ID).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.ID))):(null);
        String email = req.getParameter(ParameterNames.EMAIL);
        String name = req.getParameter(ParameterNames.NAME);
        LocalDate birthday = (req.getParameter(ParameterNames.BIRTHDAY).matches(ParameterPatterns.DATE_PATTERN))?(LocalDate.parse(req.getParameter(ParameterNames.BIRTHDAY))):(null);
        Integer payment = (req.getParameter(ParameterNames.PAYMENT).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.PAYMENT))):(null);
        Integer departmentId = (req.getParameter(ParameterNames.DEPARTMENT_ID).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID))):(null);

        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);

        Integer oldDepartmentId = (req.getParameter(AttributeNames.EMPLOYEE_DEPARTMENT_ID).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(AttributeNames.EMPLOYEE_DEPARTMENT_ID))):(null);

        if (employee.getDepartmentId() != null
                && employee.getPayment() != null
                && employeeService.update(employee)) {
            LOG.debug("Updated employee: employee --> " + employee);
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?id=" + oldDepartmentId);
        } else {
            LOG.debug("Can't update employee: employee --> " + employee);
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_ADD_OR_UPDATE_EMPLOYEE);
            req.setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
            req.setAttribute(AttributeNames.EMPLOYEE_DEPARTMENT_ID, oldDepartmentId);
            req.getRequestDispatcher(Paths.UPDATE_EMPLOYEE_JSP).forward(req, resp);
        }
    }
}
