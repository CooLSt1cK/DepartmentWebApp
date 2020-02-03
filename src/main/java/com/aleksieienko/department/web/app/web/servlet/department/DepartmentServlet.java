package com.aleksieienko.department.web.app.web.servlet.department;

import com.aleksieienko.department.web.app.Paths;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.service.DepartmentService;
import com.aleksieienko.department.web.app.web.AttributeNames;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

@WebServlet(name = "DepartmentServlet", value = "/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    private DepartmentService departmentService;

    private static final Logger LOG = Logger.getLogger(DepartmentServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        departmentService = (DepartmentService) config.getServletContext().getAttribute(AttributeNames.DEPARTMENT_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Department> list = departmentService.getAll();
        LOG.trace("Got departments' list from database: list --> " + list);
        LOG.trace("Set attribute to context: " + AttributeNames.DEPARTMENT_LIST + " --> " + list);
        request.getServletContext().setAttribute(AttributeNames.DEPARTMENT_LIST, list);
        response.sendRedirect(request.getContextPath() + Paths.INDEX_JSP);
    }
}
