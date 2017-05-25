package be.pxl.calllog.controllers;

import be.pxl.calllog.models.CallLogDao;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */

@WebServlet(name = "SearchControllerServlet", value = "/Search")
public class SearchControllerServlet extends HttpServlet {
    private CallLogDao guestBookDao = new CallLogDao();
    private List<CallLogDao> beanList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/SearchView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        guestBookDao.setUrl(getInitParameter("url"));
        guestBookDao.setUser(getInitParameter("user"));
        guestBookDao.setPassword(getInitParameter("password"));


    }

    @Override
    public void destroy() {

    }
}
