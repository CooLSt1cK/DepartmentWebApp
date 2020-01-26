package com.aleksieienko.department.web.app.db.dao.impl;

import com.aleksieienko.department.web.app.db.Constants;
import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.Fields;
import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private DBManager dbManager;

    public DepartmentDaoImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(Constants.SQL_SELECT_DEPARTMENT);
            while (rs.next()) {
                list.add(mapRow(rs));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
        return list;
    }

    @Override
    public Department get(Integer id) {
        Department department = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Constants.SQL_SELECT_DEPARTMENT_BY_ID);
            rs = pstmt.executeQuery();
            department = mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
        return department;
    }

    @Override
    public boolean add(Department department) {
        PreparedStatement stmt;
        Connection con = null;
        boolean res = true;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(Constants.SQL_INSERT_INTO_DEPARTMENT);
            stmt.setString(1, department.getName());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean update(Department department) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean res = true;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Constants.SQL_UPDATE_DEPARTMENT);
            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean delete(Integer id) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean res = true;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Constants.SQL_DELETE_DEPARTMENT);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    @Override
    public Department mapRow(ResultSet rs) {
        try {
            Department department = new Department();
            department.setId(rs.getInt(Fields.DEPARTMENT_ID));
            department.setName(rs.getString(Fields.DEPARTMENT_NAME));
            return department;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
