package com.aleksieienko.department.web.app.web.servlet.department;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
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

@WebServlet(name = "AddDepartmentServlet", value = "/AddDepartment")
public class AddDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentService = (DepartmentService) config.getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.ADD_DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter(ParameterNames.NAME);

        Department department = new Department();
        department.setName(name);

        if (!departmentService.add(department)) {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_ADD_OR_UPDATE_DEPARTMENT);
            req.setAttribute(AttributeNames.DEPARTMENT_WITHOUT_ID, department);
            req.getRequestDispatcher(Paths.ADD_DEPARTMENT_JSP).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
        }
    }
}
