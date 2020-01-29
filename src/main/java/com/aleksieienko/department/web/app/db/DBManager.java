package com.aleksieienko.department.web.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private String url;
    private String userName;
    private String password;

    public DBManager(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userName, password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return con;
    }
}
