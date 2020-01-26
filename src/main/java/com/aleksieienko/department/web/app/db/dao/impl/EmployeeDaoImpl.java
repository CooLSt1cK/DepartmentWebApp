package com.aleksieienko.department.web.app.db.dao.impl;

import com.aleksieienko.department.web.app.db.Constants;
import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.Fields;
import com.aleksieienko.department.web.app.db.dao.EmployeeDao;
import com.aleksieienko.department.web.app.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private DBManager dbManager;

    public EmployeeDaoImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        Statement stmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            stmt = con.createStatement();
            rs = stmt.executeQuery(Constants.SQL_SELECT_EMPLOYEE);
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
    public Employee get(Integer id) {
        Employee employee = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Constants.SQL_SELECT_EMPLOYEE_BY_ID);
            rs = pstmt.executeQuery();
            employee = mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            dbManager.commitAndClose(con);
        }
        return employee;
    }

    @Override
    public List<Employee> getForDepartment(Integer id) {
        List<Employee> list = new ArrayList<>();
        PreparedStatement stmt;
        ResultSet rs;
        Connection con = null;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(Constants.SQL_SELECT_EMPLOYEE_FOR_DEPARTMENT);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
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
    public boolean add(Employee employee) {
        PreparedStatement stmt;
        Connection con = null;
        boolean res = true;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            stmt = con.prepareStatement(Constants.SQL_INSERT_INTO_EMPLOYEE);
            stmt.setString(1, employee.getEmail());
            stmt.setString(2, employee.getName());
            stmt.setDate(3, Date.valueOf(employee.getBirthday()));
            stmt.setInt(4, employee.getPayment());
            stmt.setInt(5, employee.getDepartmentId());
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
    public boolean update(Employee employee) {
        PreparedStatement pstmt;
        Connection con = null;
        boolean res = true;
        try {
            con = dbManager.getConnection();
            con.setAutoCommit(false);
            pstmt = con.prepareStatement(Constants.SQL_UPDATE_EMPLOYEE);
            pstmt.setString(1, employee.getEmail());
            pstmt.setString(2, employee.getName());
            pstmt.setDate(3, Date.valueOf(employee.getBirthday()));
            pstmt.setInt(4, employee.getPayment());
            pstmt.setInt(5, employee.getDepartmentId());
            pstmt.setInt(6, employee.getId());
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
            pstmt = con.prepareStatement(Constants.SQL_DELETE_EMPLOYEE);
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
    public Employee mapRow(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt(Fields.EMPLOYEE_ID));
            employee.setEmail(rs.getString(Fields.EMPLOYEE_EMAIL));
            employee.setName(rs.getString(Fields.EMPLOYEE_NAME));
            employee.setBirthday(rs.getDate(Fields.EMPLOYEE_BIRTHDAY).toLocalDate());
            employee.setPayment(rs.getInt(Fields.EMPLOYEE_PAYMENT));
            employee.setDepartmentId(rs.getInt(Fields.EMPLOYEE_DEPARTMENT_ID));
            return employee;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
