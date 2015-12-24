
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ВОДИТЕЛЬ - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <form name="edit_driver_form" action="main_manager.jsp" method="post">
        Новое имя<input name="driverName" type="text"><br>
        Новая Фамилия<input name="driverSurname" type="text"><br>
        <input type="submit" value="РЕДАКТИРОВАТЬ"><br>
    </form>
</div>
</body>
</html>
