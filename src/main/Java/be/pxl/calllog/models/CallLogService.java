package be.pxl.calllog.models;

import be.pxl.calllog.app.CallLogStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jordy Swinnen
 */
public class CallLogService implements ICallLogService {
    CallLogDao dao = null;

    public CallLogService() {
        dao = new CallLogDao();
    }

    public CallLogDao getDao() {
        return dao;
    }

    public void setDao(CallLogDao dao) {
        this.dao = dao;
    }

    @Override
    public List<CallLogBean> search(String value) {
        List<CallLogBean> callLogList = null;

        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM calllog WHERE Name " +
                             "LIKE %?% OR Datum LIKE %?% or Bedrijf LIKE %?% or Status LIKE %?%")) {
            stmt.setString(1, "%" + value + "%");
            stmt.setString(2, "%" + value + "%");
            stmt.setString(3, "%" + value + "%");
            stmt.setString(4, "%" + value + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                callLogList = fillWithResultSet(rs);
                return callLogList;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CallLogBean> getAll() {
        List<CallLogBean> callLogList = null;
        try (Connection con = dao.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM calllog")) {
            callLogList = fillWithResultSet(rs);

            return callLogList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<CallLogBean> fillWithResultSet(ResultSet rs) throws SQLException {
        List<CallLogBean> callLogList = new ArrayList<>();

        while (rs.next()) {
            CallLogBean bean = new CallLogBean();
            bean.setId(rs.getInt(1));
            bean.setNaam(rs.getString(2));
            bean.setDatum(rs.getDate(3));
            bean.setBedrijf(rs.getString(4));
            bean.setOmschrijving(rs.getString(5));
            bean.setPrio(rs.getInt(6));
            bean.setStatus(CallLogStatus.getCallLogStatusType(rs.getString(7)));

            callLogList.add(bean);

        }
        return callLogList;
    }
}
