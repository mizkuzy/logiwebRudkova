<%--
  Created by IntelliJ IDEA.
  User: Lucy
  Date: 30.11.2015
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Фуру - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<div>
    <form name="add_van_form" action="/AddVanServlet" method="post">
        Регистрационный номер<input name="VanNumber" type="text"><br>
        Размер смены водителей<input name="DriversAmount" type="text"><br>
        Вместимость (тонн)<input name="Capacity" type="text"><br>
        <input type="submit" value="ДОБАВИТЬ"><br>
    </form>
</div>
</body>
</html>
