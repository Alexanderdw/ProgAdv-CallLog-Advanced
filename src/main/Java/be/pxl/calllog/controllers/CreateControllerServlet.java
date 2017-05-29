package be.pxl.calllog.controllers;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 */
@WebServlet("/Create")
public class CreateControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CreateView.jsp").forward(req, resp);
    }
}