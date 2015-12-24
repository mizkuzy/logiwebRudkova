<%@ page import="ru.tsystems.logiweb.entities.Van" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создать заказ - LOGIWEB</title>
</head>
<body>

<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>

<form>
    Номер заказа: &nbsp;<%=request.getSession().getAttribute("orderNumber")%>
    <div>
        Статус заказа: &nbsp;<%=request.getSession().getAttribute("orderStatus")%>
    </div>
    <div>
        <table class="table-bordered" border="2" width="2" cellspacing="2" cellpadding="2">
            <tr>
                <th>Груз</th>
                <th>Пункт отправления</th>
                <th>Пункт назначения</th>
            </tr>
            <tr>
                <td><%=request.getSession().getAttribute("good")%>
                </td>
                <td><%=request.getSession().getAttribute("city1")%>
                </td>
                <td><%=request.getSession().getAttribute("city2")%>
                </td>
            </tr>
        </table>
    </div>
    Общая масса: &nbsp;<%=request.getSession().getAttribute("mass")%><br>
</form>
<form action="/PickYellowRoutServlet" method="post">
    <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
        <input type="checkbox" name="selectedVan" value="${theCount.count}">${van}<br>
    </c:forEach>
    <input type="submit" value="Сохранить заказ">
</form>

<%--    <select onchange="<%request.getSession().setAttribute("idVan",%>value<%);%>">
        <!--<option selected>ВЫБЕРИТЕ ФУРУ</option>-->
        <% for (Van van : vans) {%>
        <option value="<%=van.getIdVan()%>"><%=van.getVanNumber()%>
        </option>
        <%}%>
        <option></option>
    </select>--%>

<%-- <table>
     <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
         <tr>
             <td>${van}</td>
         </tr>
     </c:forEach>
 </table>--%>

<%--<form name="" action="" method="">
    <c:forEach items="${appropriateVans}" var="van" varStatus="theCount">
        ${van}<input type="checkbox" onclick="<%request.getSession().setAttribute();%>" value="${van}">

    </c:forEach>
</form>--%>

<%-- <form name="" action="" method="">
    <table>
        <c:forEach items="${appropriateVans}" var="van" varStatus="appropriateVansCount">
            <tr>
                <td>count: ${appropriateVansCount.count}</td>
            </tr>
            <tr>
            ${van}<input type="checkbox" %>" value="${van}">
            </tr>
        </c:forEach>
    </table>
</form> --%>

<%--  <a href="cp_client_change_contract?contractId=${contract.id}"> --%>


<%--  <c:forEach items="${appropriateVans}" var="van" varStatus="appropriateVansCount">
      <div id="id="divIDNo${appropriateVansCount}">" onclick="<%request.getSession().setAttribute("idVan",id);%>">

      </div>
  </c:forEach>--%>

<%--   <c:forEach items="${appropriateVans}" var="van" varStatus="appropriateVansCount">
       <div>
           <c:out value="${van}"></c:out>
           <p><a href="/appropriateVans/${van.id}"> </a> </p>
       </div>
   </c:forEach>--%>

<!-- <div>
     <input id="add_driver" type="button" value="Добавить водителя"><br>
 </div>-->


<%--<form name="save_order" action="/PickYellowRoutServlet" method="post">
    <input type="submit" value="Сохранить заказ">
</form>--%>


</body>
</html>