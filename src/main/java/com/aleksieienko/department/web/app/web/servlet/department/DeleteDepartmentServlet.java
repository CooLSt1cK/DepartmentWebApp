package com.aleksieienko.department.web.app.web.servlet.department;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import com.aleksieienko.department.web.app.web.ParameterNames;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DeleteDepartmentServlet", value = "/DeleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentService = (DepartmentService) config.getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        Department department = departmentService.getById(id);
        req.setAttribute(AttributeNames.DEPARTMENT_BY_ID, department);
        req.getRequestDispatcher(Paths.DELETE_DEPARTMENT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.parseInt(req.getParameter(ParameterNames.ID));
        if (!departmentService.deleteById(id)) {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, "Department is not exist");
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + Paths.DEPARTMENT_SERVLET));
        } else {
            resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
        }
    }
}
