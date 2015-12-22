<%--
  Created by IntelliJ IDEA.
  User: Lucy
  Date: 01.12.2015
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить водителя - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager.jsp">
        <input type="submit" value="ДОМОЙ">
    </form>
</div>
<div>
    <form name="add_driver_form" action="/AddDriverServlet" method="post">
        Имя<input name="driverName" type="text"><br>
        Фамилия<input name="driverSurname" type="text"><br>
        e-mail<input name="email" type="text"><br>
        password<input name="password" type="password"><br>
        <input type="submit" value="ДОБАВИТЬ"><br>
    </form>
</div>
</body>
</html>
