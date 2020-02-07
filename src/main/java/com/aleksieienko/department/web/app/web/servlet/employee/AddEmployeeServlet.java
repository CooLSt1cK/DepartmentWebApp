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

@WebServlet(name = "AddEmployeeServlet", value = "/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    private static final Logger LOG = Logger.getLogger(AddEmployeeServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeService) config.getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer departmentId = (req.getParameter(ParameterNames.DEPARTMENT_ID).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID))):(null);
        req.setAttribute(AttributeNames.OLDER_THEN, LocalDate.now().minusYears(18));
        req.setAttribute(AttributeNames.DEPARTMENT_ID, departmentId);
        req.getRequestDispatcher(Paths.ADD_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter(ParameterNames.EMAIL);
        String name = req.getParameter(ParameterNames.NAME);
        LocalDate birthday = (req.getParameter(ParameterNames.BIRTHDAY).matches(ParameterPatterns.DATE_PATTERN))?(LocalDate.parse(req.getParameter(ParameterNames.BIRTHDAY))):(null);
        Integer payment = (req.getParameter(ParameterNames.PAYMENT).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.PAYMENT))):(null);
        Integer departmentId = (req.getParameter(ParameterNames.DEPARTMENT_ID).matches(ParameterPatterns.INTEGER_PATTERN))?(Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID))):(null);

        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);

        if (employee.getDepartmentId() != null
                && employee.getPayment() != null
                && employeeService.add(employee)) {
            LOG.debug("Added employee: employee --> " + employee);
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?" + ParameterNames.ID + "=" + departmentId);
        } else {
            LOG.debug("Can't add employee: employee --> " + employee);
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_ADD_OR_UPDATE_EMPLOYEE);
            req.setAttribute(AttributeNames.EMPLOYEE_WITHOUT_ID, employee);
            req.setAttribute(AttributeNames.DEPARTMENT_ID, departmentId);
            req.getRequestDispatcher(Paths.ADD_EMPLOYEE_JSP).forward(req, resp);
        }
    }
}
