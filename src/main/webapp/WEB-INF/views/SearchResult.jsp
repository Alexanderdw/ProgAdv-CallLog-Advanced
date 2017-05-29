<%--
  Created by IntelliJ IDEA.
  User: swinn
  Date: 25/05/2017
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="bodyDiv">
    <h1>Search Result</h1>
    <table class="scroll">
        <thead>
        <tr>
            <th>id</th>
            <th>naam</th>
            <th>bedrijf</th>
            <th>status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${CallLogBean}" var="CallLog" varStatus="Status">
            <tr>
                <td>${CallLog.id}</td>
                <td>${CallLog.naam}</td>
                <td>${CallLog.bedrijf}</td>
                <td>${CallLog.status}</td>
                <td>
                    <a href="CallLogBean?id=${CallLog.id}">Show Details</a>
                </td>
                <td>
                    <a href="CallLogBean?id=${CallLog.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


