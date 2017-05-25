package be.pxl.calllog.models;

import java.util.List;

/**
 * @author Jordy Swinnen
 */
public interface CallLogService {
    public CallLogBean search(String value);
    public List<CallLogBean> getAll();
}
