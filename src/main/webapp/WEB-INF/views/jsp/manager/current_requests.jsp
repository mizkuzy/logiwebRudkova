<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Current requests - LOGIWEB</title>

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
            <a class="navbar-brand" href="main_manager">LOGIWEB</a>
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
<div class="container">
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <div class="background-set">
            <img src="/resources/img/truck1.jpg" alt="truck1">
        </div>
        <div>
            <h1>Current requests</h1>

            <div class="row">
                <div class="col-md-6">
                    <div class="colored-yellow">
                        <form action="create_order" method="get">
                            <input type="hidden" name="currentRoutLabel" value="yellow"/>
                            <%--<img href="create_order" src="/resources/img/yellow.png" class="img-responsive img-thumbnail">--%>
                            WAIT:${yellowRoutRequestsSize} requests<br/>
                            <button class="btn-warning" type="submit" title="Handle" value="HANDLE">HANDLE</button>
                        </form>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="colored-green">
                        <form action="create_order" method="get">
                            <input type="hidden" name="currentRoutLabel" value="green"/>
                            WAIT:${greenRoutRequestsSize} requests <br/>
                            <%--<img src="/resources/img/green.png" class="img-responsive img-thumbnail">--%>
                            <button class="btn-warning" type="submit" title="Handle" value="HANDLE">HANDLE</button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="colored-purple">
                        <form action="create_order" method="get">
                            <input type="hidden" name="currentRoutLabel" value="purple"/>
                            WAIT: ${purpleRoutRequestsSize} requests <br/>
                            <%--<img src="/resources/img/purple.png" class="img-responsive img-responsive img-thumbnail">--%>
                            <button class="btn-warning" type="submit" title="Handle" value="HANDLE">HANDLE</button>
                        </form>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="colored-blue">
                        <form action="create_order" method="get">
                            <input type="hidden" name="currentRoutLabel" value="blue"/>
                            WAIT:${blueRoutRequestsSize} requests <br/>
                            <%--<img src="/resources/img/blue.png" class="img-responsive img-responsive img-thumbnail">--%>
                            <button class="btn-warning" type="submit" title="Handle" value="HANDLE">HANDLE</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>