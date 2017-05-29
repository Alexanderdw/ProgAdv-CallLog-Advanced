package be.pxl.calllog.models;

import be.pxl.calllog.app.CallLogStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jordy Swinnen
 */
public class CallLogService implements ICallLogService {
    private CallLogDao dao = null;

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
                     .prepareStatement("SELECT * FROM calllog WHERE concat_ws(', ',Name, Datum, Bedrijf, Status) LIKE ?")) {
            stmt.setString(1, "'%" + value + "%'");
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

    @Override
    public CallLogBean getCallLogById(int Id) {
        CallLogBean logBean = null;
        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM calllog WHERE id = ?")) {
            stmt.setInt(1, Id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    logBean = new CallLogBean();
                    logBean.setId(rs.getInt(1));
                    logBean.setNaam(rs.getString(2));
                    logBean.setDatum(rs.getDate(3));
                    logBean.setBedrijf(rs.getString(4));
                    logBean.setOmschrijving(rs.getString(5));
                    logBean.setPrio(rs.getInt(6));
                    logBean.setStatus(CallLogStatus.getCallLogStatusType(rs.getString(7)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return logBean;
    }

    private List<CallLogBean> fillWithResultSet(ResultSet rs) throws SQLException {
        List<CallLogBean> callLogList = new ArrayList<>();

        while (rs.next()) {
            CallLogBean logBean = new CallLogBean();
            logBean.setId(rs.getInt(1));
            logBean.setNaam(rs.getString(2));
            logBean.setDatum(rs.getDate(3));
            logBean.setBedrijf(rs.getString(4));
            logBean.setOmschrijving(rs.getString(5));
            logBean.setPrio(rs.getInt(6));
            logBean.setStatus(CallLogStatus.getCallLogStatusType(rs.getString(7)));

            callLogList.add(logBean);

        }
        return callLogList;
    }
}
