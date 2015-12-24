<%@ page import="ru.tsystems.logiweb.entities.Van" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.tsystems.logiweb.entities.Order" %>
<%@ page import="ru.tsystems.logiweb.entities.Request" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order - LOGIWEB</title>
</head>
<body>
<h1>Order</h1>

<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>

<form>
    <% Order order = (Order) request.getSession().getAttribute("order");%>
    Номер заказа: &nbsp;<%=order.getNumber()%>
    <div>
        Статус заказа: &nbsp;<%=order.getStatus()%>
    </div>
    <div>
        <table class="table-bordered" border="2" width="2" cellspacing="2" cellpadding="2">
            <tr>
                <th>Груз</th>
                <th>Пункт отправления</th>
                <th>Пункт назначения</th>
            </tr>
            <% List<Request> currentRequests = order.getRequests();
                for (Request r : currentRequests) {%>
            <tr>
                <td><%=r.getGoodForRequest().getName()%>
                </td>
                <td><%=r.getRoutForRequest().getCity1()%>
                </td>
                <td><%=r.getRoutForRequest().getCity2()%>
                </td>
                <% }
                %>
            </tr>
        </table>
    </div>
    Общая масса: &nbsp;<%=request.getSession().getAttribute("mass")%><br>
</form>
<form action="save_order" method="post">
    <%--<c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
        <input type="hidden" name="htmlFormName" value="purple"/>
        <input type="checkbox" name="selectedVan" value="${theCount.count}">${van}<br>
    </c:forEach>--%>

    <%-- <input type="hidden" name="htmlFormName" value="${theCount.count}"/>--%>
    <select>
        <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
            <option value="${theCount.count}">${van}</option>
        </c:forEach>
    </select>
    <br>
    <input type="submit" value="SAVE ORDER">
</form>
</body>
</html>