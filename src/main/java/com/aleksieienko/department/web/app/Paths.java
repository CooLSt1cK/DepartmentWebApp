package com.aleksieienko.department.web.app;

public class Paths {
    public static final String DB_SETTINGS = "src/main/resources/db_settings.property";
    public static final String LOG4J_PROPERTIES = "../resources/log4j.properties";

    public static final String ADD_DEPARTMENT_JSP = "/WEB-INF/jsp/department/addDepartment.jsp";
    public static final String DELETE_DEPARTMENT_JSP = "/WEB-INF/jsp/department/deleteDepartment.jsp";
    public static final String UPDATE_DEPARTMENT_JSP = "/WEB-INF/jsp/department/updateDepartment.jsp";
    public static final String DEPARTMENT_SERVLET = "/DepartmentServlet";

    public static final String INDEX_JSP = "/index.jsp";

    public static final String EMPLOYEE_JSP = "/WEB-INF/jsp/employee/employee.jsp";
    public static final String ADD_EMPLOYEE_JSP = "/WEB-INF/jsp/employee/addEmployee.jsp";
    public static final String DELETE_EMPLOYEE_JSP = "/WEB-INF/jsp/employee/deleteEmployee.jsp";
    public static final String EMPLOYEE_SERVLET = "/EmployeeServlet";
    public static final String UPDATE_EMPLOYEE_JSP = "/WEB-INF/jsp/employee/updateEmployee.jsp";


    private Paths() {
    }
}
