
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- СДЕЛАТЬ САЙТ НА ДВУХ ЯЗЫКАХ-->
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIWEB</title>
</head>
<body>
<h1>MANAGER'S APP</h1>

<div>
    <form name="form_pick_up_orders" action="new_request" method="get">
        <input type="submit" title="New request" value="NEW REQUEST">
    </form>
</div>
<div>
    <form name="form_pick_up_orders" action="/PickUpOrdersServlet" method="get">
        <input type="submit" title="Pick up requests" value="PICKUP REQUESTS">
    </form>
</div>
<div>
    <form name="orders_list" action="/OrdersListServlet" method="get">
        <input type="submit" title="Orders List" value="ORDERS LIST">
    </form>
</div>
<div>
    <form name="vans" action="/VanServlet" method="get">
        <input type="submit" title="VANS" value="VANS">
    </form>
</div>
<div>
    <form name="drivers" action="/DriverServlet" method="get">
        <input type="submit" title="DRIVERS" value="DRIVERS">
    </form>
</div>
</body>
</html>