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

@WebServlet("/DeleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService = (DepartmentService) getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        Department department = departmentService.get(id);
        req.getSession().setAttribute(AttributeNames.DEPARTMENT_BY_ID, department);
        req.getRequestDispatcher(Paths.DELETE_DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        if (!departmentService.delete(id)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Department is not exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        } else {
            req.getSession().setAttribute(AttributeNames.DEPARTMENT_BY_ID, null);
            resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
        }
    }
}
