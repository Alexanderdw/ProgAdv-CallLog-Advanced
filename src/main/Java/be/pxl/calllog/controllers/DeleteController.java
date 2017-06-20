package be.pxl.calllog.controllers;

import be.pxl.calllog.dao.CallLogDao;
import be.pxl.calllog.models.*;
import be.pxl.calllog.services.CallLogServiceImpl;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */

@WebServlet(name = "DeleteController", value = "/Delete")
public class DeleteController extends HttpServlet {
    private CallLogServiceImpl service = new CallLogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CallLogBean logBean = service.getCallLogById(Integer.parseInt(id));
        req.setAttribute("CallLog", logBean);
        req.getRequestDispatcher("/WEB-INF/views/DeleteView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("delId");
        boolean success;
        success = service.deleteCallLogById(Integer.parseInt(id));
        if (success) {
            req.setAttribute("Message", "was succesfull!");
            req.getRequestDispatcher("/WEB-INF/views/DeleteResult.jsp").forward(req, resp);
        } else {
            req.setAttribute("Message", " Failed...");
            req.getRequestDispatcher("/WEB-INF/views/DeleteResult.jsp").forward(req, resp);
        }

    }

    @Override
    public void init() throws ServletException {
        CallLogDao callLogDao = new CallLogDao();

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
