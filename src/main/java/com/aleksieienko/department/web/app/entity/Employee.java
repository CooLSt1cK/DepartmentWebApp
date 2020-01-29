package com.aleksieienko.department.web.app.entity;

import com.aleksieienko.department.web.app.service.Patterns;
import java.time.LocalDate;

public class Employee {
    private Integer id;



    private String email;
    private String name;
    private LocalDate birthday;
    private Integer payment;
    private Integer departmentId;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", payment=" + payment +
                ", department_id=" + departmentId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isValid() {
        return email.matches(Patterns.EMPLOYEE_EMAIL_PATTERN)
                && name.matches(Patterns.EMPLOYEE_NAME_PATTERN)
                && email.length() <= 255
                && isEighteen();
    }

    private boolean isEighteen() {
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusYears(18);
        return birthday.isBefore(localDate);
    }
}
