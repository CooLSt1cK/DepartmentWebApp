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
import java.time.LocalDate;

@WebServlet("/UpdateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = (EmployeeService) getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeService.get(id);
        req.getSession().setAttribute(AttributeNames.EMPLOYEE_BY_ID, employee);
        req.getRequestDispatcher(Paths.UPDATE_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        Integer payment = Integer.parseInt(req.getParameter("payment"));
        Integer departmentId = Integer.parseInt(req.getParameter("departmentId"));
        if (!employeeService.update(id, email, name, birthday, payment, departmentId)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Not valid or employee's email like this already exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        }
        req.getSession().setAttribute(AttributeNames.EMPLOYEE_BY_ID, null);
        resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET);
    }
}
