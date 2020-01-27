package com.aleksieienko.department.web.app.web.servlet.department;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateDepartment")
public class UpdateDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService = (DepartmentService) getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Department department = departmentService.get(id);
        req.getSession().setAttribute(AttributeNames.DEPARTMENT_BY_ID, department);
        req.getRequestDispatcher(Paths.UPDATE_DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        if (!departmentService.update(id, name)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Not valid or department's name like this already exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        } else {
            req.getSession().setAttribute(AttributeNames.DEPARTMENT_BY_ID, null);
            resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
        }
    }
}
