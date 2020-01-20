package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.entity.Entity;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created on 19.01.2020
 *
 * @author K.Aleksieienko
 */
public interface DAO {
    List<?> getAll();

    boolean add(Entity o);

    boolean update(Entity o);

    boolean delete(Integer id);

    Entity mapRow(ResultSet rs);
}