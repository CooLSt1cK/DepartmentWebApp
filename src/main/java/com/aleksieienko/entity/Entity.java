package com.aleksieienko.entity;

/**
 * Created on 19.01.2020
 *
 * @author K.Aleksieienko
 */
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
