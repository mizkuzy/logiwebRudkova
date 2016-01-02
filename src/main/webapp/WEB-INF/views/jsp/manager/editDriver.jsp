<%@ page import="ru.tsystems.logiweb.entities.Driver" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Driver - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <form name="edit_driver_form" action="editDriver" method="post">
        Name<input name="driverName" type="text" value="${selectedDriver.name}" required><br>
        Surname<input name="driverSurname" type="text" value="${selectedDriver.surname}" required><br>
        <input type="submit" title="edit" value="EDIT"><br>
    </form>
</div>
</body>
</html>
