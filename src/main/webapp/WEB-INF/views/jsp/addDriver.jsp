<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить водителя - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
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
