package com.aleksieienko.department.web.app.db.dao;

import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.Fields;
import com.aleksieienko.department.web.app.db.Util;
import com.aleksieienko.department.web.app.entity.Employee;
import com.aleksieienko.department.web.app.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements DAO {
    @Override
    public List<?> getAll() {
        List<Employee> list = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(Util.SQL__SELECT_EMPLOYEE);
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
            stmt = con.prepareStatement(Util.SQL__INSERT_INTO_EMPLOYEE);
            Employee e = (Employee) o;
            stmt.setString(1, e.getEmail());
            stmt.setString(2, e.getName());
            stmt.setString(3, e.getBirthday());
            stmt.setInt(4, e.getPayment());
            stmt.setInt(5, e.getDepartmentId());
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
            pstmt = con.prepareStatement(Util.SQL__UPDATE_EMPLOYEE);
            Employee e = (Employee) o;
            pstmt.setString(1, e.getEmail());
            pstmt.setString(2, e.getName());
            pstmt.setString(3, e.getBirthday());
            pstmt.setInt(4, e.getPayment());
            pstmt.setInt(5, e.getDepartmentId());
            pstmt.setInt(6, o.getId());
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
            pstmt = con.prepareStatement(Util.SQL__DELETE_EMPLOYEE);
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
    public Employee mapRow(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt(Fields.EMPLOYEE__ID));
            employee.setEmail(rs.getString(Fields.EMPLOYEE__EMAIL));
            employee.setName(rs.getString(Fields.EMPLOYEE__NAME));
            employee.setBirthday(rs.getString(Fields.EMPLOYEE__BIRTHDAY));
            employee.setPayment(rs.getInt(Fields.EMPLOYEE__PAYMENT));
            employee.setDepartmentId(rs.getInt(Fields.EMPLOYEE__DEPARTMENT_ID));
            return employee;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
