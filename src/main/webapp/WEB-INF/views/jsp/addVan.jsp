<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить Фуру - LOGIWEB</title>
</head>
<body>
<div>
    <form name="home" action="main_manager" method="get">
        <input type="submit" value="HOME">
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
