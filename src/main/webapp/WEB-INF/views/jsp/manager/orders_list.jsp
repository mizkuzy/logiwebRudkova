<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:forEach items="${ordersPROCESS}" var="order">
        <p>Order number: &nbsp; ${order.number}
        </p>

        <p>Van number: &nbsp; ${order.van}
        </p>

        <p>
            Drivers list:<br>
            <c:forEach items="${order.drivers}" var="driver">
                ${driver}<br/>
            </c:forEach>
        </p>

        Routs points:
        <table cellspacing="2" cellpadding="2">
            <tr>
                <th>GOOD</th>
                <th>CITY FROM</th>
                <th>CITY TO</th>
            </tr>
            <c:forEach items="${order.requests}" var="request">
                <tr>
                    <td>
                            ${request.goodForRequest.name}
                    </td>
                    <td>
                            ${request.routForRequest.city1}
                    </td>
                    <td>
                            ${request.routForRequest.city2}
                    </td>
                </tr>
            </c:forEach>
        </table>
        <hr noshade="noshade"/>
    </c:forEach>


</div>

</body>
</html>
