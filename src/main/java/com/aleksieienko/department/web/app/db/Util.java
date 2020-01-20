package com.aleksieienko.department.web.app.db;

public class Util {
    public static final String SQL__SELECT_DEPARTMENT = "SELECT * FROM department";
    public static final String SQL__INSERT_INTO_DEPARTMENT = "INSERT INTO department VALUES (,?)";
    public static final String SQL__DELETE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
    public static final String SQL__UPDATE_DEPARTMENT = "UPDATE department SET name = ? WHERE id = ?";

    public static final String SQL__INSERT_INTO_EMPLOYEE = "INSERT INTO employee VALUES (,?,?,?,?,?)";
    public static final String SQL__SELECT_EMPLOYEE = "SELECT * FROM employee";
    public static final String SQL__UPDATE_EMPLOYEE = "UPDATE employee " +
            "SET email = ?, name = ?, birthday = ?, payment = ?, department_id = ? " +
            "WHERE id = ?";
    public static final String SQL__DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
}
