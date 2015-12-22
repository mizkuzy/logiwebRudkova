<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.tsystems.logiweb.entities.Request" %><%--
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Собрать заявки - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<div>
    <form name="pick_yellow" action="/PickYellowRoutServlet" method="get">
        <% ArrayList<Request> yellowRequests = (ArrayList<Request>) request.getSession().getAttribute("yellowRoutRequests");%>
        ЖЁЛТЫЙ МАРШРУТ <br/>
        ОЖИДАЕТ ОБРАБОТКИ:<%=yellowRequests.size()%>  штук<br/>
        <input type="submit" title="Обработать" value="ОБРАБОТАТЬ">
    </form>
</div>
<div>
    <form name="pick_green" action="" method="get">
        <% ArrayList<Request> greenRequests = (ArrayList<Request>) request.getSession().getAttribute("greenRoutRequests");%>
        ЗЕЛЕНЫЙ МАРШРУТ <br/>
        ОЖИДАЕТ ОБРАБОТКИ: <%=greenRequests.size()%> штук <br/>
        <input type="submit" title="Обработать" value="ОБРАБОТАТЬ">
    </form>
</div>
<div>
    <form name="pick_purple" action="" method="get">
        <% ArrayList<Request> purpleRequests = (ArrayList<Request>) request.getSession().getAttribute("purpleRoutRequests");%>
        ФИОЛЕТОВЫЙ МАРШРУТ <br/>
        ОЖИДАЕТ ОБРАБОТКИ: <%=purpleRequests.size()%> штук <br/>
        <input type="submit" title="Обработать" value="ОБРАБОТАТЬ">
    </form>
</div>
<div>
    <form name="pick_blue" action="" method="get">
        <% ArrayList<Request> blueRequests = (ArrayList<Request>) request.getSession().getAttribute("blueRoutRequests");%>
        СИНИЙ МАРШРУТ <br/>
        ОЖИДАЕТ ОБРАБОТКИ: <%=blueRequests.size()%> штук <br/>
        <input type="submit" title="Обработать" value="ОБРАБОТАТЬ">
    </form>
</div>
</body>
</html>