package be.pxl.calllog.interfaces;

import be.pxl.calllog.models.CallLogBean;

import java.util.List;

/**
 * @author Jordy Swinnen
 */
public interface CallLogService {

    List<CallLogBean> search(String value);

    List<CallLogBean> getAll();

    CallLogBean getCallLogById(int Id);

    boolean deleteCallLogById(int id);

    void insertNewCallLog(CallLogBean logBean);
}
