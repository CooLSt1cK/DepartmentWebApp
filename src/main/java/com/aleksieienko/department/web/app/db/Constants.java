package com.aleksieienko.department.web.app.db;

public class Constants {
    public static final String SQL_SELECT_DEPARTMENT = "SELECT * FROM department";
    public static final String SQL_INSERT_INTO_DEPARTMENT = "INSERT INTO department VALUES ( NULL, ?)";
    public static final String SQL_DELETE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
    public static final String SQL_UPDATE_DEPARTMENT = "UPDATE department SET name = ? WHERE id = ?";
    public static final String SQL_SELECT_DEPARTMENT_BY_ID = "SELECT * FROM department WHERE id = ?";

    public static final String SQL_INSERT_INTO_EMPLOYEE = "INSERT INTO employee VALUES ( NULL, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT_EMPLOYEE = "SELECT * FROM employee";
    public static final String SQL_SELECT_EMPLOYEE_FOR_DEPARTMENT = "SELECT * FROM employee WHERE department_id = ?";
    public static final String SQL_UPDATE_EMPLOYEE = "UPDATE employee " +
            "SET email = ?, name = ?, birthday = ?, payment = ?, department_id = ? " +
            "WHERE id = ?";
    public static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
    public static final String SQL_SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";


    private Constants() {
    }
}
