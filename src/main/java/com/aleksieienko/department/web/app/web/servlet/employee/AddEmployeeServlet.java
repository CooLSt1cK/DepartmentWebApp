package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = (EmployeeService) getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.ADD_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = (Integer) req.getSession().getAttribute(AttributeNames.DEPARTMENT_ID);
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        Integer payment = Integer.parseInt(req.getParameter("payment"));
        if (!employeeService.add(email, name, birthday, payment, id)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Not valid or employee's email like this already exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET);
        }
    }
}
