package be.pxl.calllog.controllers;

import be.pxl.calllog.dao.CallLogDao;
import be.pxl.calllog.models.*;
import be.pxl.calllog.services.CallLogServiceImpl;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */

@WebServlet(loadOnStartup = 1, name = "SearchController", value = "/Search")
public class SearchController extends HttpServlet {
    private CallLogServiceImpl service = new CallLogServiceImpl();
    private List<CallLogBean> beanList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            beanList = service.getAll();
            req.setAttribute("CallLogs", beanList);
            req.getRequestDispatcher("/WEB-INF/views/SearchView.jsp").forward(req, resp);
        } else {
            CallLogBean logBean = service.getCallLogById(Integer.parseInt(id));
            req.setAttribute("CallLog", logBean);
            req.getRequestDispatcher("/WEB-INF/views/SearchDetail.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("logSearch");
        beanList = service.search(value);
        req.setAttribute("CallLogs", beanList);
        req.setAttribute("Search", value);
        req.getRequestDispatcher("/WEB-INF/views/SearchResult.jsp").forward(req, resp);
    }

    @Override
    public void init() throws ServletException {
        CallLogDao callLogDao = new CallLogDao();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        callLogDao.setUrl(getInitParameter("url"));
        callLogDao.setUser(getInitParameter("user"));
        callLogDao.setPassword(getInitParameter("password"));

        service.setDao(callLogDao);
    }

    @Override
    public void destroy() {
        beanList = null;
        service = null;
    }
}
