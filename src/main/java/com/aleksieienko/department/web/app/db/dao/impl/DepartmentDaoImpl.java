package com.aleksieienko.department.web.app.db.dao.impl;

import com.aleksieienko.department.web.app.db.Constants;
import com.aleksieienko.department.web.app.db.DBManager;
import com.aleksieienko.department.web.app.db.Fields;
import com.aleksieienko.department.web.app.db.dao.DepartmentDao;
import com.aleksieienko.department.web.app.entity.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DepartmentDaoImpl implements DepartmentDao {
    private DBManager dbManager;
    private static final Logger LOG = Logger.getLogger(DepartmentDaoImpl.class);

    public DepartmentDaoImpl(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    @Override
    public List<Department> getAll() {
        List<Department> list = new ArrayList<>();
        Connection con = dbManager.getConnection();
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(Constants.SQL_SELECT_DEPARTMENT)) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            LOG.warn(Constants.QUERY_ERROR_MESSAGE + ex.getMessage());
        } finally {
            dbManager.commitAndClose(con);
        }
        return list;
    }

    @Override
    public Department getById(Integer id) {
        Department department = null;
        Connection con = dbManager.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(Constants.SQL_SELECT_DEPARTMENT_BY_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                department = mapRow(rs);
            }
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            LOG.warn(Constants.QUERY_ERROR_MESSAGE + ex.getMessage());
        } finally {
            dbManager.commitAndClose(con);
        }
        return department;
    }

    @Override
    public boolean add(Department department) {
        Connection con = dbManager.getConnection();
        boolean res = true;
        try (PreparedStatement stmt = con.prepareStatement(Constants.SQL_INSERT_INTO_DEPARTMENT)) {
            stmt.setString(1, department.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            LOG.warn(Constants.QUERY_ERROR_MESSAGE + ex.getMessage());
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean update(Department department) {
        Connection con = dbManager.getConnection();
        boolean res = true;
        try (PreparedStatement pstmt = con.prepareStatement(Constants.SQL_UPDATE_DEPARTMENT)) {
            pstmt.setString(1, department.getName());
            pstmt.setInt(2, department.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            LOG.warn(Constants.QUERY_ERROR_MESSAGE + ex.getMessage());
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    @Override
    public boolean deleteById(Integer id) {
        Connection con = dbManager.getConnection();
        boolean res = true;
        try (PreparedStatement pstmt = con.prepareStatement(Constants.SQL_DELETE_DEPARTMENT)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            dbManager.rollbackAndClose(con);
            LOG.warn(Constants.QUERY_ERROR_MESSAGE + ex.getMessage());
            res = false;
        } finally {
            dbManager.commitAndClose(con);
        }
        return res;
    }

    private Department mapRow(ResultSet rs) {
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
