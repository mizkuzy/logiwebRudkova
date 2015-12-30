<%@ page import="ru.tsystems.logiweb.entities.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tsystems.logiweb.entities.Request" %>
<%@ page import="ru.tsystems.logiweb.entities.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders list - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <h1>Orders list</h1>

    <% List<Order> ordersPROCESS = (ArrayList<Order>) request.getSession().getAttribute("ordersPROCESS");
        for (Order o : ordersPROCESS) {
    %>
    <p>Order number: &nbsp; <%=o.getNumber()%>
    </p>

    <p>Van number: &nbsp;<%=o.getVan()%>
    </p>

    <p>
        Drivers list:<br>
        <%
            List<Driver> drivers = o.getDrivers();
            for (Driver d :
                    drivers) {
        %>
        <%=d%><br>
        <%
            }
        %>
    </p>

        Routs points:
    <table>
        <tr>
            <th>GOOD</th>
            <th>CITY FROM</th>
            <th>CITY TO</th>
        </tr>

        <%
            List<Request> requests = o.getRequests();
            for (Request r :
                    requests) {%>
        <tr>
            <td>
                <%=r.getGoodForRequest().getName()%>
            </td>
            <td>
                <%=r.getRoutForRequest().getCity1()%>
            </td>
            <td>
                <%=r.getRoutForRequest().getCity2()%>
            </td>
        </tr>

        <% }
        %>

    </table>

    <hr noshade="noshade"/>
    <% }
    %>
</div>


</body>
</html>
