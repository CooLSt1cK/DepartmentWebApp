package com.aleksieienko.department.web.app.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/WEB-INF/jsp/*",
        servletNames = {"DepartmentServlet", "EmployeeServlet", "AddDepartmentServlet",
                "UpdateDepartmentServlet", "DeleteDepartmentServlet", "AddEmployeeServlet",
                "UpdateEmployeeServlet", "DeleteEmployeeServlet"})
@WebInitParam(name = "encoding", value = "UTF-8")
public class EncodingFilter implements Filter {
    private String encoding;

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String requestEncoding = request.getCharacterEncoding();
        if (requestEncoding == null) {
            request.setCharacterEncoding(encoding);
        }

        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) {
        encoding = fConfig.getInitParameter("encoding");
    }
}
