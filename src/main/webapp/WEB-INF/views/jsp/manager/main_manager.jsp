<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIWEB</title>
</head>
<body>
<h1>MANAGER'S APP</h1>

<div>
    <a href="/logout/" class="exit link">
        <span>Выход</span>
    </a>
</div>
<h3>Hello, <security:authentication property="principal.username"/>!</h3>

<div>
    <form name="form_new_request" action="new_request" method="get">
        <input type="submit" title="New request" value="NEW REQUEST">
    </form>
</div>
<div>
    <form name="form_pick_up_requests" action="pick_up_requests" method="get">
        <input type="submit" title="Pick up requests" value="PICKUP REQUESTS">
    </form>
</div>
<div>
    <form name="form_orders_list" action="orders_list" method="get">
        <input type="submit" title="Orders List" value="ORDERS LIST">
    </form>
</div>
<div>
    <form name="form_vans" action="vans" method="get">
        <input type="submit" title="VANS" value="VANS">
    </form>
</div>
<div>
    <form name="form_drivers" action="drivers" method="get">
        <input type="submit" title="DRIVERS" value="DRIVERS">
    </form>
</div>
</body>
</html>