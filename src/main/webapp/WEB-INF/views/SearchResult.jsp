<%--
  Created by IntelliJ IDEA.
  User: swinn
  Date: 25/05/2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="bodyDiv">
    <h1>Search Result</h1>
    <div style="overflow-x:auto;">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Company</th>
                <th>Status</th>
                <th>Details</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${CallLogs}" var="CallLog">
                <tr>
                    <td>${CallLog.id}</td>
                    <td>${CallLog.naam}</td>
                    <td>${CallLog.bedrijf}</td>
                    <td>${CallLog.status}</td>
                    <td>
                        <a href="Search?id=${CallLog.id}">Show Details</a>
                    </td>
                    <td>
                        <a href="Delete?id=${CallLog.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


