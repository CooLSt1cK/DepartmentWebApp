package com.aleksieienko.department.web.app.entity;

public abstract class Entity {
    private Integer id;

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
