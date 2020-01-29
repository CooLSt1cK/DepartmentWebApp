package com.aleksieienko.department.web.app.service;

public class Patterns {
    public static final String EMPLOYEE_NAME_PATTERN = "[A-Z][a-z]{1,29} [A-Z][a-z]{1,29}";
    public static final String EMPLOYEE_EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+\\/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&’*+\\/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    public static final String DEPARTMENT_NAME_PATTERN = "([A-Z][a-z]{1,30}( |\\b)){1,4}";

    private Patterns() {
    }
}
