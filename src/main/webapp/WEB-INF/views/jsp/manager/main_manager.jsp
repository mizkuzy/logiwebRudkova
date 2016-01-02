<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIWEB</title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/common.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
        <button type="submit" class="btn btn-default" title="New request" value="NEW REQUEST">NEW REQUEST</button>
        <%-- <input type="submit" title="New request" value="NEW REQUEST">--%>
    </form>
</div>
<div>
    <form name="form_pick_up_requests" action="pick_up_requests" method="get">
        <button type="submit" class="btn btn-default" title="Pick up requests" value="PICKUP REQUESTS">PICKUP REQUESTS</button>
    </form>
</div>
<div>
    <form name="form_orders_list" action="orders_list" method="get">
        <button type="submit" class="btn btn-default" title="Orders List" value="ORDERS LIST">ORDERS LIST</button>
        <%--<input type="submit" title="Orders List" value="ORDERS LIST">--%>
    </form>
</div>
<div>
    <form name="form_vans" action="vans" method="get">
        <button type="submit" class="btn btn-default"  title="VANS" value="VANS">VANS</button>
        <%--<input type="submit" title="VANS" value="VANS">--%>
    </form>
</div>
<div>
    <form name="form_drivers" action="drivers" method="get">
        <button type="submit" class="btn btn-default" title="DRIVERS" value="DRIVERS">DRIVERS</button>
        <%--<input type="submit" title="DRIVERS" value="DRIVERS">--%>
    </form>
</div>
</body>
</html>