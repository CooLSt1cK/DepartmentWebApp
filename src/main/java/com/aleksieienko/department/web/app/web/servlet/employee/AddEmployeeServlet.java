package com.aleksieienko.department.web.app.web.servlet.employee;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.service.EmployeeService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import com.aleksieienko.department.web.app.web.ParameterNames;
import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddEmployeeServlet", value = "/AddEmployee")
public class AddEmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        employeeService = (EmployeeService) config.getServletContext().getAttribute(AttributeNames.EMPLOYEE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer departmentId = Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID));
        req.setAttribute(AttributeNames.DEPARTMENT_ID, departmentId);
        req.getRequestDispatcher(Paths.ADD_EMPLOYEE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter(ParameterNames.EMAIL);
        String name = req.getParameter(ParameterNames.NAME);
        LocalDate birthday = LocalDate.parse(req.getParameter(ParameterNames.BIRTHDAY));
        Integer payment = Integer.parseInt(req.getParameter(ParameterNames.PAYMENT));
        Integer departmentId = Integer.parseInt(req.getParameter(ParameterNames.DEPARTMENT_ID));

        Employee employee = new Employee();
        employee.setEmail(email);
        employee.setName(name);
        employee.setBirthday(birthday);
        employee.setPayment(payment);
        employee.setDepartmentId(departmentId);

        if (!employeeService.add(employee)) {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, "Not valid or employee's email like this already exist");
            req.setAttribute(AttributeNames.EMPLOYEE_WITHOUT_ID, employee);
            req.setAttribute(AttributeNames.DEPARTMENT_ID, departmentId);
            req.getRequestDispatcher(Paths.ADD_EMPLOYEE_JSP).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.EMPLOYEE_SERVLET + "?" + ParameterNames.ID + "=" + departmentId);
        }
    }
}
