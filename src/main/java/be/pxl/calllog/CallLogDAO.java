package be.pxl.calllog;

import java.sql.*;
import java.util.*;

/**
 * @author Jordy Swinnen
 */
public class CallLogDAO {
    private String url;
    private String user;
    private String password;

    public CallLogDAO() {
    }

    public CallLogDAO(String url, String user, String password) {
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

    public void insertCallLogList(Collection<CallLog> callLogList) {

        try (Connection con = getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("INSERT INTO CallLog(id, naam, datum, bedrijf, omschrijving, prio, status)" +
                             " VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
            for (CallLog callLog : callLogList) {
                stmt.setInt(1, callLog.getId());
                stmt.setString(2, callLog.getNaam());
                stmt.setDate(3, new java.sql.Date(callLog.getDatum().getTime()));
                stmt.setString(4, callLog.getBedrijf());
                stmt.setString(5, callLog.getOmschrijving());
                stmt.setInt(6, callLog.getPrio());
                stmt.setString(7, String.valueOf(callLog.getStatus()));
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
