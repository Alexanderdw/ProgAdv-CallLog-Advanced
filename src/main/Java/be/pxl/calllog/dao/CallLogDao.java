package be.pxl.calllog.dao;

import java.sql.*;

/**
 * @author Jordy Swinnen
 */
public class CallLogDao {
    private String url;
    private String user;
    private String password;

    public CallLogDao() {
    }

    public CallLogDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }


}