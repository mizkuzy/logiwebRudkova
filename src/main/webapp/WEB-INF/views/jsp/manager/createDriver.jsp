<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create driver - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
    </form>
</div>
<div>
    <form name="add_driver_form" action="addDriver" method="post">
        Name<input name="driverName" type="text" required><br>
        Surname<input name="driverSurname" type="text" required><br>
        e-mail<input name="email" type="text" required><br>
        password<input name="password" type="password" required><br>
        <input type="submit" title="add" value="ADD" required><br>
    </form>
</div>
</body>
</html>