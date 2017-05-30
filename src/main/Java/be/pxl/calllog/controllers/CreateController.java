package be.pxl.calllog.controllers;

import be.pxl.calllog.app.CallLogStatus;
import be.pxl.calllog.models.CallLogBean;
import be.pxl.calllog.models.CallLogDao;
import be.pxl.calllog.models.CallLogService;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */
@WebServlet(name = "CreateController", value = "/Create")
public class CreateController extends HttpServlet {
    private CallLogService service = new CallLogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CreateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat format = new SimpleDateFormat("DD-MM-yyyy");

        CallLogBean logBean = new CallLogBean();
        try {
            logBean.setId(Integer.valueOf(req.getParameter("id")));
            logBean.setNaam(req.getParameter("naam"));
            logBean.setDatum(format.parse(req.getParameter("datum")));
            logBean.setBedrijf(req.getParameter("bedrijf"));
            logBean.setOmschrijving(req.getParameter("omschrijving"));
            logBean.setPrio(Integer.parseInt(req.getParameter("prio")));
            logBean.setStatus(CallLogStatus.getCallLogStatusType(req.getParameter("status")));

            service.insertNewCallLog(logBean);
            req.setAttribute("Message", "was succesfull!");
            req.getRequestDispatcher("/WEB-INF/views/CreateResult.jsp").forward(req, resp);

        } catch (ParseException e) {
            req.setAttribute("Message", " Failed...");
            req.getRequestDispatcher("/WEB-INF/views/CreateResult.jsp").forward(req, resp);
            e.printStackTrace();
        }


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
        service = null;
    }
}