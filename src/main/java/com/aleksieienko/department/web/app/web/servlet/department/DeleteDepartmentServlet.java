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
import org.apache.log4j.Logger;

@WebServlet(name = "DeleteDepartmentServlet", value = "/DeleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;

    private static final Logger LOG = Logger.getLogger(DeleteDepartmentServlet.class);

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
        if (departmentService.deleteById(id)) {
            resp.sendRedirect(req.getContextPath() + Paths.DEPARTMENT_SERVLET);
        } else {
            req.setAttribute(AttributeNames.ERROR_MESSAGE, ErrorMessages.ERROR_DELETE_DEPARTMENT);
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + Paths.DEPARTMENT_SERVLET));
        }
    }
}
