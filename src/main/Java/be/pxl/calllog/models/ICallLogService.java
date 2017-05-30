package be.pxl.calllog.models;

import java.util.List;

/**
 * @author Jordy Swinnen
 */
public interface ICallLogService {

    List<CallLogBean> search(String value);

    List<CallLogBean> getAll();

    CallLogBean getCallLogById(int Id);

    boolean deleteCallLogById(int id);
}
