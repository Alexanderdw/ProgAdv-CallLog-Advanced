package be.pxl.calllog.controllers;

import be.pxl.calllog.app.CallLog;
import be.pxl.calllog.app.CallLogFactory;
import be.pxl.calllog.dao.CallLogDao;
import be.pxl.calllog.models.CallLogBean;
import be.pxl.calllog.services.CallLogServiceImpl;

import java.io.*;
import java.nio.file.Paths;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

/**
 * @author Jordy Swinnen
 *         https://stackoverflow.com/questions/40250814/read-a-line-of-a-file-using-an-inputstream
 *         https://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
 */
@WebServlet("/Import")
public class ImportController extends HttpServlet {
    CallLogServiceImpl service = new CallLogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/ImportView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part filePart = req.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        InputStream fileContent = filePart.getInputStream();
        try (Scanner scanner = new Scanner(fileContent)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                CallLog callLog = CallLogFactory.createCallLog(line);
                service.insertNewCallLog(callLog);
            }
        }
        req.getRequestDispatcher("/WEB-INF/views/ImportView.jsp").forward(req, resp);
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
