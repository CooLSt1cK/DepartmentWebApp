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

@WebServlet(name = "UpdateEmployeeServlet", value = "/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeService) config.getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        Employee employee = employeeService.getById(id);
        req.setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
        req.setAttribute(AttributeNames.EMPLOYEE_DEPARTMENT_ID, employee.getDepartmentId());
        req.getRequestDispatcher(Paths.UPDATE_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        String email = req.getParameter(ParameterNames.EMAIL);
        String name = req.getParameter(ParameterNames.NAME);
        LocalDate birthday = LocalDate.parse(req.getParameter(ParameterNames.BIRTHDAY));
        Integer payment = Integer.parseInt(req.getParameter(ParameterNames.PAYMENT));
        Integer departmentId = Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID));

        Employee employee = new Employee();
        employee.setId(id);
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);

        Integer oldDepartmentId = Integer.parseInt(req.getParameter(AttributeNames.EMPLOYEE_DEPARTMENT_ID));

        if (!employeeService.update(employee)) {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_ADD_OR_UPDATE_EMPLOYEE);
            req.setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
            req.setAttribute(AttributeNames.EMPLOYEE_DEPARTMENT_ID, oldDepartmentId);
            req.getRequestDispatcher(Paths.UPDATE_EMPLOYEE_JSP).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?id=" + oldDepartmentId);
        }
    }
}
