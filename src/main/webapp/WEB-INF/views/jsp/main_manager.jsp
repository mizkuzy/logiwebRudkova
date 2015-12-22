
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- СДЕЛАТЬ САЙТ НА ДВУХ ЯЗЫКАХ-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIWEB</title>
</head>
<body>
<h1>Приложение для сотрудника</h1>
<!--
РЕализовать, если будет время
<div>
    <h2>Новая заявка</h2>
</div>

-->
<div>
    <form name="form_pick_up_orders" action="/PickUpOrdersServlet" method="get">
        <input type="submit" title="Собрать заявки" value="СОБРАТЬ ЗАЯВКИ">
    </form>
</div>
<div>
    <form name="orders_list" action="/OrdersListServlet" method="get">
        <input type="submit" title="Список заказов" value="СПИСОК ЗАКАЗОВ">
    </form>
</div>
<div>
    <form name="vans" action="/VanServlet" method="get">
        <input type="submit" title="Фуры" value="ФУРЫ">
    </form>
</div>
<div>
    <form name="drivers" action="/DriverServlet" method="get">
        <input type="submit" title="Водители" value="ВОДИТЕЛИ">
    </form>
</div>
</body>
</html>