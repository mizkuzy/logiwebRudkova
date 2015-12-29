<%@ page import="ru.tsystems.logiweb.entities.Driver" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Drivers - LOGIWEB</title>
</head>
<body>

<h1>Drivers</h1>

<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>

<table border="2" width="2" cellspacing="2" cellpadding="2">
    <caption>DRIVERS LIST</caption>
    <tr>
        <th>
            Personal Number
        </th>
        <th>
            Name
        </th>
        <th>
            Surname
        </th>
        <th>
            Work hours
        </th>
        <th>
            Current state
        </th>
        <td>
            Work Status
        </td>
        <td>

        </td>
    </tr>
    <% ArrayList<Driver> drivers = (ArrayList<Driver>) request.getSession().getAttribute("drivers");
        for (int i = 0; i < drivers.size(); i++) {

    %>
    <tr>
       <td>
            <%--<%=driver.getEmployee().getPersonalNumber()%>--%>
        </td>
        <td>
            <%=drivers.get(i).getName()%>
        </td>
        <td>
            <%=drivers.get(i).getSurname()%>
        </td>
        <td>
            <%=drivers.get(i).getWorkHours()%>
        </td>
        <td>
            <%=drivers.get(i).getState()%>
        </td>
        <td>
            <%=drivers.get(i).getStatusDriver()%>
        </td>
        <td>
            <div>
                <form name="edit" action="getDriverForEdit" method="get">
                    <input type="hidden" name="selectedDriver" value="<%=drivers.get(i).getId()%>"/>
                    <input type="submit" title="Edit" value="EDIT">
                </form>
            </div>
            <div>
                <form name="delete" action="deleteDriver" method="get">
                    <input type="hidden" name="selectedDriver" value="<%=drivers.get(i).getId()%>"/>
                    <input type="submit" title="Delete" value="DELETE">
                </form>
            </div>
        </td>
    </tr>
    <%} %>
</table>

<div>
    <form name="add" action="createDriver" method="get">
        <input type="submit" title="Add new driver" value="NEW">
    </form>
</div>

</body>
</html>