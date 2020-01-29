package com.aleksieienko.department.web.app.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
