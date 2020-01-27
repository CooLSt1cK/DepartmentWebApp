package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = (EmployeeService) getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.get(id);
        req.getSession().setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
        req.getRequestDispatcher(Paths.DELETE_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (!employeeService.delete(id)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Employee is not exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        } else {
            req.getSession().setAttribute(AttributeNames.EMPLOYEE_BY_ID, null);
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET);
        }
    }
}
