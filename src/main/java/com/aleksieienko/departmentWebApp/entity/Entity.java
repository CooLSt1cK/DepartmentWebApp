package com.aleksieienko.departmentWebApp.entity;

public abstract class Entity {
    public Integer id;

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
