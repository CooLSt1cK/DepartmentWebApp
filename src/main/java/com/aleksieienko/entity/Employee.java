package com.aleksieienko.entity;

/**
 * Created on 19.01.2020
 *
 * @author K.Aleksieienko
 */
public class Employee extends Entity {
    private String email;
    private String name;
    private String birthday;
    private Integer payment;
    private Integer department_id;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", payment=" + payment +
                ", department_id=" + department_id +
                '}';
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }
}
