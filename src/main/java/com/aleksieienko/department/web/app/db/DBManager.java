package com.aleksieienko.department.web.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class DBManager {

    private static final Logger LOG = Logger.getLogger(DBManager.class);

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
            LOG.error("Cannot rollback or close connection");
        }
    }

    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            LOG.warn("Cannot commit or close connection");
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, userName, password);
            con.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.warn("Cannot get connection");
        }
        return con;
    }
}
