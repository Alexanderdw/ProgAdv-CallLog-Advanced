<%--
  Created by IntelliJ IDEA.
  User: swinn
  Date: 30/05/2017
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="bodyDiv">
    <h1>Delete</h1>
    id: ${CallLog.id} <br/>
    naam: ${CallLog.naam} <br/>
    datum: ${CallLog.datum} <br/>
    bedrijf: ${CallLog.bedrijf} <br/>
    omschrijving: ${CallLog.omschrijving} <br/>
    prio: ${CallLog.prio} <br/>
    status: ${CallLog.status} <br/><br/>

    <div style="color: red;"> Proceed with delete? This action is permanent. <br/>
        You will not be able to undo changes*
    </div>
    <br/>
    <form method="post"></form>
    <input type="button" value="Return"
           onclick="window.location.href='/CallLogAdvanced/Search'">
    <input type="submit" value="DELETE"/>
    </form>

    <div style="position: fixed; bottom: 60px;">
        *However you may manually insert a deleted (or new) row again <a href="/CallLogAdvanced/Create">here</a>.
</div>

</div>