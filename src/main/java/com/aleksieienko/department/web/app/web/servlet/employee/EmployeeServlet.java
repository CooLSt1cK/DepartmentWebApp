package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = (EmployeeService) getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        List<Employee> list = employeeService.getForDepartment(id);
        request.getSession().setAttribute(AttributeNames.DEPARTMENT_ID, id);
        request.getSession().setAttribute(AttributeNames.EMPLOYEE_LIST, list);
        response.sendRedirect(request.getContextPath() + Paths.EMPLOYEE_JSP);
    }

}
