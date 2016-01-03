<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Orders list - LOGIWEB</title>
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

<div class="container text-center">
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

        <div class="background-set">
            <img class="img-responsive" src="/resources/img/truck1.jpg" alt="truck1">
        </div>

        <table class="table table-hover my-settings">
            <caption>Orders list</caption>
            <c:forEach items="${ordersPROCESS}" var="order">
                <tr class="td-sp">
                    <th>Order number:</th>
                    <td>${order.number}</td>
                    <td>
                        <form name="finish" action="finishOrder" method="get">
                            <input type="hidden" name="selectedOrder" value="${order.idOrder}"/>
                            <button class="btn-success" type="submit" title="Finish" value="FINISH">FINISH</button>
                        </form>
                    </td>
                </tr>
                <tr class="td-sp">
                    <th>Van number:</th>
                    <td>${order.van}</td>
                    <td></td>
                </tr>
                <tr class="td-sp">
                    <th>Drivers list:</th>
                    <td>
                        <c:forEach items="${order.drivers}" var="driver">
                            ${driver}<br/>
                        </c:forEach>
                    </td>
                    <td></td>
                </tr>
                <tr class="td-sp">
                    <th>Routs points:</th>
                    <td>
                        <table>
                            <tr>
                                <th>GOOD</th>
                                <th>CITY FROM</th>
                                <th>CITY TO</th>
                            </tr>
                            <c:forEach items="${order.requests}" var="request">
                                <tr>
                                    <td>
                                            ${request.goodForRequest.name}
                                    </td>
                                    <td>
                                            ${request.routForRequest.city1}
                                    </td>
                                    <td>
                                            ${request.routForRequest.city2}
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
