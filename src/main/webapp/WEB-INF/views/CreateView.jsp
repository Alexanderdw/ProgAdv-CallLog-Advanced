<%--
  Created by IntelliJ IDEA.
  User: swinn
  Date: 25/05/2017
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="bodyDiv">
    <h1>Create new call log</h1>
    <form method="POST">
        <div class="form-row">
            <label for="id">id</label>
            <input id="id" name="id" type="number" required="required" />
        </div>
        <div class="form-row">
            <label for="naam">Name</label>
            <input id="naam" name="naam" required="required"/>
        </div>
        <div class="form-row">
            <label for="datum">Date</label>
            <input id="datum" type="date" name="datum" required="required"/>
        </div>
        <div class="form-row">
            <label for="bedrijf">Company</label>
            <input id="bedrijf" name="bedrijf" required="required"/>
        </div>
        <div class="form-row">
            <label for="omschrijving">Description</label>
            <input id="omschrijving" name="omschrijving"/>
        </div>
        <div class="form-row">
            <label for="prio">Prio</label>
            <input id="prio" type="number" name="prio" required="required"/>
        </div>
        <div class="form-row">
            <label for="status">Status</label>
            <select id="status" name="status">
                <option value="OPEN">OPEN</option>
                <option value="IN PROGRESS">IN_PROGRESS</option>
                <option value="IGNORE" >IGNORE</option>
                <option value="CLOSED">CLOSED</option>
                <option value="" selected>NULL</option>
            </select>
        </div>
        <br/>
        <div class="form-row">
            <input type="submit" value="Create">
        </div>
    </form>


</div>
