package be.pxl.calllog.services;

import be.pxl.calllog.app.CallLogStatus;
import be.pxl.calllog.dao.CallLogDao;
import be.pxl.calllog.interfaces.CallLogService;
import be.pxl.calllog.models.CallLogBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jordy Swinnen
 */
public class CallLogServiceImpl implements CallLogService {
    private CallLogDao dao = null;

    public CallLogServiceImpl() {
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
        value.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");

        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM calllog WHERE concat_ws(', ',Naam, Datum, Bedrijf, Status) LIKE ?")) {
            stmt.setString(1, "%" + value + "%");
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
    public CallLogBean getCallLogById(int id) {
        CallLogBean logBean = null;
        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("SELECT * FROM calllog " +
                             "WHERE id = ?")) {
            stmt.setInt(1, id);
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

    @Override
    public boolean deleteCallLogById(int id) {
        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("DELETE FROM calllog " +
                             "WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
            con.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void insertNewCallLog(CallLogBean logBean) {
        try (Connection con = dao.getConnection();
             PreparedStatement stmt = con
                     .prepareStatement("INSERT INTO CallLog(id, naam, datum, bedrijf, omschrijving, prio, status)" +
                             " VALUES ( ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, logBean.getId());
            stmt.setString(2, logBean.getNaam());
            stmt.setDate(3, new java.sql.Date(logBean.getDatum().getTime()));
            stmt.setString(4, logBean.getBedrijf());
            stmt.setString(5, logBean.getOmschrijving());
            stmt.setInt(6, logBean.getPrio());
            stmt.setString(7, String.valueOf(logBean.getStatus()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


        private List<CallLogBean> fillWithResultSet (ResultSet rs) throws SQLException {
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
