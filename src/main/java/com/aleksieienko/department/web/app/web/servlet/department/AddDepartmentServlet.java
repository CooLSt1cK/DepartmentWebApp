package com.aleksieienko.department.web.app.web.servlet.department;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.web.AttributeNames;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddDepartment")
public class AddDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService = (DepartmentService) getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Paths.ADD_DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (!departmentService.add(name)) {
            req.getSession().setAttribute(AttributeNames.ERROR_MESSAGE, "Not valid or department's name like this already exist");
            resp.sendRedirect(req.getContextPath() + Paths.ERROR_CONTROLLER);
        }
        resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
    }
}
