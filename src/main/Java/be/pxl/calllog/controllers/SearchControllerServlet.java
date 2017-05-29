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

@WebServlet(name = "SearchControllerServlet", value = "/Search")
public class SearchControllerServlet extends HttpServlet {
    private CallLogService service = new CallLogService(); //service
    private List<CallLogBean> beanList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        beanList = service.getAll();
        req.getRequestDispatcher("/WEB-INF/views/SearchView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getAttribute("search");
    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        CallLogDao callLogDao = new CallLogDao();


        callLogDao.setUrl(getInitParameter("url"));
        callLogDao.setUser(getInitParameter("user"));
        callLogDao.setPassword(getInitParameter("password"));

        service.setDao(callLogDao);


    }

    @Override
    public void destroy() {

    }
}
