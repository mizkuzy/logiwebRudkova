<%@ page import="ru.tsystems.logiweb.entities.Van" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ФУРА - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<div>
    <form name="edit_van_form" action="main_manager.jsp" method="post">
        id ФУРЫ
       <%-- <% Van van = (Van) request.getSession().getAttribute("currentVan");%>
        <%=van.getIdVan()%>
       --%>
        <br>
        Регистрационный номер<input name="VanNumber" type="text"><br>
        Размер смены водителей<input name="DriversAmount" type="text"><br>
        Вместимость (тонн)<input name="Capacity" type="text"><br>
        <input type="submit" value="РЕДАКТИРОВАТЬ"><br>
    </form>
</div>
</body>
</html>
