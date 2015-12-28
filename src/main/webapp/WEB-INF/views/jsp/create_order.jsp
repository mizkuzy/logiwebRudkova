<%@ page import="ru.tsystems.logiweb.entities.Order" %>
<%@ page import="ru.tsystems.logiweb.entities.Request" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order - LOGIWEB</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
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
    Общая масса: &nbsp;${mass}<%--<%=request.getSession().getAttribute("mass")%>--%><br>
</form>
<script>
    jQuery(function () {
        var max = <%=session.getAttribute("maxCheckboxSelections")%>;
        var checkboxes = $('input[type="checkbox"]');

        checkboxes.change(function () {
            var current = checkboxes.filter(':checked').length;
            checkboxes.filter(':not(:checked)').prop('disabled', current >= max);
        });
    });
</script>

<form action="save_order" method="get">
    <h4>Choose van</h4>
    <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
        <%-- <input type="hidden" name="htmlFormName" value="purple"/>--%>
        <input type="checkbox" name="selectedVan" value="${theCount.count}" max="1">${van}<br>
    </c:forEach>

    <br>
    <h4>Choose ${maxCheckboxSelections-1} drivers</h4>
    <c:forEach items="${appropriateDrivers}" var="driver" varStatus="theCount">
        <%-- <input type="hidden" name="htmlFormName" value="purple"/>--%>
        <input type="checkbox" name="selectedDriver" value="${theCount.count}" max="3">${driver}<br>
    </c:forEach>

    <input type="submit" value="SAVE ORDER">
    <%-- <input type="hidden" name="htmlFormName" value="${theCount.count}"/>--%>

    <%--ВОТ ЭТОТ КУСОК вслплывающий список--%>
    <%-- <select>
          <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
              <option name="${theCount.count}" value="${theCount.count}">${van}</option>
          </c:forEach>
      </select>--%>


    <%--<c:choose>
        <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
            <c:when test="${idVan=='theCount.count'}">
                ${van}
            </c:when>
        </c:forEach>
    </c:choose>--%>


</form>
</body>
</html>