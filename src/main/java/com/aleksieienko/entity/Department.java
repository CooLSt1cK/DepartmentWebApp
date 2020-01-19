package com.aleksieienko.entity;

/**
 * Created on 19.01.2020
 *
 * @author K.Aleksieienko
 */
public class Department extends Entity {
    private String name;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
