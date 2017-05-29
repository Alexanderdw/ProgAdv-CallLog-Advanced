package be.pxl.calllog.controllers;

import be.pxl.calllog.models.*;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */

@WebServlet(name = "DeleteControllerServlet", value = "/Delete")
public class DeleteControllerServlet extends HttpServlet {
    private CallLogService service = new CallLogService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
            CallLogBean logBean = service.getCallLogById(Integer.parseInt(id));
            req.setAttribute("CallLog", logBean);
            req.getRequestDispatcher("/WEB-INF/views/DeleteView.jsp").forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 30/05/2017 DELETE schrijven in service 
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
