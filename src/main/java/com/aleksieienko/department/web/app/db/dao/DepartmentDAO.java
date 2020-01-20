package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.Fields;
import com.aleksieienko.department.web.app.db.Util;
import com.aleksieienko.department.web.app.entity.Department;
import com.aleksieienko.department.web.app.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements DAO {

    @Override
    public List<?> getAll() {
        List<Department> list = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(Util.SQL__SELECT_DEPARTMENT);
            while (rs.next())
                list.add(mapRow(rs));
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return list;
    }

    @Override
    public boolean add(Entity o) {
        PreparedStatement stmt;
        Connection con = null;
        boolean res = true;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(Util.SQL__INSERT_INTO_DEPARTMENT);
            stmt.setString(1, ((Department) o).getName());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean update(Entity o) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean res = true;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Util.SQL__UPDATE_DEPARTMENT);
            pstmt.setString(1, ((Department) o).getName());
            pstmt.setInt(2, o.getId());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean delete(Integer id) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean res = true;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Util.SQL__DELETE_DEPARTMENT);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
            res = false;
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return res;
    }

    @Override
    public Department mapRow(ResultSet rs) {
        try {
            Department department = new Department();
            department.setId(rs.getInt(Fields.DEPARTMENT__ID));
            department.setName(rs.getString(Fields.DEPARTMENT__NAME));
            return department;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
