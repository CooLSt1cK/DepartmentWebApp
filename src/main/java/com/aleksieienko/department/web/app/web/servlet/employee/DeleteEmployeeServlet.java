package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import com.aleksieienko.department.web.app.web.ParameterNames;
import com.aleksieienko.department.web.app.web.servlet.ErrorMessages;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteEmployeeServlet", value = "/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
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
        req.setAttribute(AttributeNames.DEPARTMENT_ID, employee.getDepartmentId());
        req.getRequestDispatcher(Paths.DELETE_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        if (!employeeService.deleteById(id)) {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_DELETE_EMPLOYEE);
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?" + ParameterNames.ID + "=" + req.getParameter(ParameterNames.DEPARTMENT_ID)));
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?" + ParameterNames.ID + "=" + req.getParameter(ParameterNames.DEPARTMENT_ID));
        }
    }
}
